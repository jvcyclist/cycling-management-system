package pl.karas.cyclingmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.User;
import pl.karas.cyclingmanagementsystem.repository.UserRepository;
import pl.karas.cyclingmanagementsystem.service.UserAdminService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class AdminUserController {

    @Autowired
    UserAdminService userAdminService;

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

    //deleteUserMapping

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable String id){
        Optional<User> optUserById = userAdminService.getUserById(Long.valueOf(id));
        if (optUserById.isPresent()) {

        }

        return ResponseEntity.ok(optUserById.get());
    }

}
