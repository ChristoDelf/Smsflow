package be.techmgt.smsflow.controllers;

import be.techmgt.smsflow.models.entity.User;
import be.techmgt.smsflow.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    public UserController(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
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

    @PutMapping("/user/{id}/update")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {

        if (userService.findById(id) == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
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

        return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}/delete")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        if (userService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.findById(id);
        user = userService.delete(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user/whoami")
    public ResponseEntity whoAmI(Principal principal) {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }

    private User processUser(@NotNull User user) {

        if (user.getId() == null) {
            return userService.register(user, false);
        }

        return userService.update(user);
    }
}