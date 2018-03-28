package be.techmgt.smsflow.services;


import be.techmgt.smsflow.models.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService {

    User register(User user, boolean isAdmin);
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    User update(User user);
    User delete(User user);

}
