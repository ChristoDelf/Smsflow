package be.techmgt.smsflow.controllers;

import be.techmgt.smsflow.models.entity.User;
import be.techmgt.smsflow.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll()
                .stream()
                .peek(u -> u.setPassword(null))
                .collect(Collectors.toList());
    }
}
