package be.techmgt.smsflow.services.impl;

import be.techmgt.smsflow.models.dto.UserFormDTO;
import be.techmgt.smsflow.models.entity.Authority;
import be.techmgt.smsflow.models.entity.User;
import be.techmgt.smsflow.models.mappers.UserMapper;
import be.techmgt.smsflow.repositories.AuthorityRepository;
import be.techmgt.smsflow.repositories.UserRepository;
import be.techmgt.smsflow.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public User register(User user, boolean isAdmin){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(new ArrayList<>(Collections.singletonList(createOrGetAuthority("ROLE_USER"))));

        if(isAdmin){
            user.getAuthorities().add(createOrGetAuthority("ROLE_ADMIN"));
        }

        user.setCreditsLeft(0);
        user.setCreditsTotal(0);

        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        return userRepository.save(user);

    }

    public Authority createOrGetAuthority(String authority) {

        Authority found = authorityRepository.findByAuthority(authority);

        if (found == null) {
            found = new Authority(authority);
            authorityRepository.save(found);
        }

        return found;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserFormDTO findById(Long id) {
        return userMapper.toFormDto(userRepository.findOne(id));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username) == null ? null : userRepository.findByUsername(username);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User delete(User user) {
        user.setEnabled(false);
        return userRepository.save(user);

    }

//    private List<UserDTO> toDtoList(List<User> users) {
//
//        List<UserDTO> dtoList = new ArrayList<>();
//
//        users.forEach((u) -> dtoList.add(u.toDto()));
//        return dtoList;
//    }
}
