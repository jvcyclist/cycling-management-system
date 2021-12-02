package pl.karas.cyclingmanagementsystem.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.karas.cyclingmanagementsystem.model.User;

import java.util.List;
import java.util.Optional;

public interface UserAdminService {

    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User updateUser(User user);

}
