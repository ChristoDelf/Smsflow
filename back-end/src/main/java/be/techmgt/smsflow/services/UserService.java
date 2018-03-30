package be.techmgt.smsflow.services;

import be.techmgt.smsflow.models.dto.UserDTO;
import be.techmgt.smsflow.models.dto.UserFormDTO;
import be.techmgt.smsflow.models.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDTO register(UserDTO userdto, boolean isAdmin);
    List<UserFormDTO> findAllFormDTO();
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    User findByUsername(String username);
    UserDTO update(UserDTO userdto);
    void delete(UserDTO user);
    UserFormDTO loadUserByUsername(String principal);
}
