package be.techmgt.smsflow.controllers;

import be.techmgt.smsflow.models.dto.UserDTO;
import be.techmgt.smsflow.models.dto.UserFormDTO;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public void createUser(@RequestBody UserDTO user) {
        processUser(user);
    }

    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) {

        return userService.findById(id);
    }

    @PutMapping("/user/{id}/update")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {

        if (userService.findById(id) == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        UserDTO userToUpdate = userService.findById(id);

        processUser(userToUpdate);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}/delete")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        if (userService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO user = userService.findById(id);
        userService.delete(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/whoami")
    public ResponseEntity whoAmI(Principal principal) {
        UserFormDTO user =  userService.loadUserByUsername(principal.getName());
        user.password = null;
        return ResponseEntity.ok(user);
    }

    private UserDTO processUser(@NotNull UserDTO userdto) {

        if (userdto.id == null) {
            return userService.register(userdto, false);
        }

        return userService.update(userdto);
    }
}