package be.techmgt.smsflow.controllers;

import be.techmgt.smsflow.models.dto.UserDTO;
import be.techmgt.smsflow.models.dto.UserFormDTO;
import be.techmgt.smsflow.repositories.UserRepository;
import be.techmgt.smsflow.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers(){
        return userService.findAll();
    }
}
