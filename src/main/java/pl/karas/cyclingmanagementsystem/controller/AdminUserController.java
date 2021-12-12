package pl.karas.cyclingmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.ActivationLink;
import pl.karas.cyclingmanagementsystem.model.User;
import pl.karas.cyclingmanagementsystem.service.ActivationLinkService;
import pl.karas.cyclingmanagementsystem.service.UserAdminService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class AdminUserController {

    @Autowired
    UserAdminService userAdminService;

    @Autowired
    ActivationLinkService activationLinkService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userAdminService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{id}")
    public ResponseEntity getUserById(@PathVariable String id){
        Optional<User> optUserById = userAdminService.getUserById(Long.valueOf(id));
        return ResponseEntity.ok(optUserById.get());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable String id){
        Optional<User> optUserById = userAdminService.getUserById(Long.valueOf(id));
        if (optUserById.isPresent()) {
            User user = optUserById.get();
            Optional<ActivationLink> optionalActivationLink = activationLinkService.getActivationLinkByUserId(user.getId());
            if(optionalActivationLink.isPresent()) {
                activationLinkService.deleteActivationLinkById(optionalActivationLink.get().getId());
            }
            userAdminService.deleteUser(user);
        }
        return ResponseEntity.ok().build();
    }

}
