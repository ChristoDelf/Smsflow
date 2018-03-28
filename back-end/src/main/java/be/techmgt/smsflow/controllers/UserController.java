package be.techmgt.smsflow.controllers;

import be.techmgt.smsflow.models.entity.User;
import be.techmgt.smsflow.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public void createUser(@RequestBody User user) {

        processUser(user);
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {

        return userService.findById(id);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {

        if (userService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User userToUpdate = userService.findById(id);

        userToUpdate.setCompanyName(user.getCompanyName());
        userToUpdate.setNameOfContact(user.getNameOfContact());
        userToUpdate.setPhoneNumber(user.getPhoneNumber());
        userToUpdate.setStreet(user.getStreet());
        userToUpdate.setCity(user.getCity());
        userToUpdate.setPostalCode(user.getPostalCode());
        userToUpdate.setCountry(user.getCountry());
        userToUpdate.setTvaNumber(user.getTvaNumber());

        userToUpdate = processUser(userToUpdate);

        return new ResponseEntity(userToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        if (userService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.findById(id);
        user = userService.delete(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private User processUser(@NotNull User user) {

        if (user.getId() == null) {
            return userService.register(user, false);
        }

        return userService.update(user);
    }
}