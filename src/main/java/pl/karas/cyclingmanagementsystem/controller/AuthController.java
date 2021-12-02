package pl.karas.cyclingmanagementsystem.controller;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.karas.cyclingmanagementsystem.model.*;
import pl.karas.cyclingmanagementsystem.payload.request.ActivateRequest;
import pl.karas.cyclingmanagementsystem.payload.request.LoginRequest;
import pl.karas.cyclingmanagementsystem.payload.request.SignupRequest;
import pl.karas.cyclingmanagementsystem.payload.response.JwtResponse;
import pl.karas.cyclingmanagementsystem.payload.response.MessageResponse;
import pl.karas.cyclingmanagementsystem.repository.RoleRepository;
import pl.karas.cyclingmanagementsystem.repository.UserRepository;
import pl.karas.cyclingmanagementsystem.security.jwt.JwtUtils;
import pl.karas.cyclingmanagementsystem.security.services.UserDetailsImpl;
import pl.karas.cyclingmanagementsystem.service.ActivationLinkService;
import pl.karas.cyclingmanagementsystem.service.EmailSenderService;

import static pl.karas.cyclingmanagementsystem.utils.MapStringRoleUtil.mapStringERole;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    ActivationLinkService activationLinkService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        log.debug("login request: {} {} ", loginRequest.getUsername(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        log.info("Login account state: {}", userDetails.getAccountState() );
        if(!userDetails.getAccountState().equals(AccountStatus.ACTIVE.toString())){
            return new ResponseEntity("Account is not active", HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user =  User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(UUID.randomUUID().toString())
                .accountStatus(AccountStatus.LINK_SENT.toString())
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .build();


        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role "+ role + " is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role "+ role + " is not found."));
                        roles.add(modRole);

                        break;
                    case "user":
                        Role zakRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role "+ role + " is not found."));
                        roles.add(zakRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(mapStringERole.get(role))
                                .orElseThrow(() -> new RuntimeException("Error: Role "+ role + " is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        User savedUser = userRepository.save(user);

        UUID activationToken = UUID.randomUUID();

        ActivationLink activationLink = ActivationLink.builder()
                .activationToken(activationToken.toString())
                .activationLink("http://localhost:4200/account-activation?activationCode=" + activationToken)
                .user(savedUser)
                .build();

        ActivationLink savedActivationLink = activationLinkService.save(activationLink);

        emailSenderService.sendEmail("patryk1karas@gmail.com",
                "Cycling Management App - Aktywuj konto",
                 "To jest twój link aktywacyjny:\n"
                + savedActivationLink.getActivationLink()
        );

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/account-activation")
    ResponseEntity<?> activateUser(@RequestBody ActivateRequest activateRequest){
        String activationCode = activateRequest.getActivationCode();

        ActivationLink activationLink = activationLinkService.getActivationLinkByActivationToken(activationCode).get();

        User user = userRepository.getById(activationLink.getUser().getId());

        if(!(user != null || activateRequest.getMail().equals(user.getEmail())) && user.getAccountStatus().equals("ACTIVE")){
            return ResponseEntity.badRequest().build();
        }

        user.setPassword(encoder.encode(activateRequest.getPassword()));
        user.setAccountStatus("ACTIVE");
        userRepository.save(user);

        emailSenderService.sendEmail("patryk1karas@gmail.com",
                "Cycling Management App - Twoje konto zostało aktywowane",
                "Twoje konto zostało aktyowane !"
        );

        return  ResponseEntity.ok().build();
    }
}