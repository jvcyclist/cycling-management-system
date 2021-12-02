package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.User;
import pl.karas.cyclingmanagementsystem.repository.UserRepository;
import pl.karas.cyclingmanagementsystem.service.UserAdminService;

import java.util.List;
import java.util.Optional;

@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
