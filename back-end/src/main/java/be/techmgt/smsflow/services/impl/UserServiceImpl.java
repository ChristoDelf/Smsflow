package be.techmgt.smsflow.services.impl;

import be.techmgt.smsflow.models.dto.UserDTO;
import be.techmgt.smsflow.models.dto.UserFormDTO;
import be.techmgt.smsflow.models.entity.Authority;
import be.techmgt.smsflow.models.entity.User;
import be.techmgt.smsflow.models.mappers.UserMapper;
import be.techmgt.smsflow.repositories.AuthorityRepository;
import be.techmgt.smsflow.repositories.UserRepository;
import be.techmgt.smsflow.services.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserDetailsService userDetailsService;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UserFormDTO loadUserByUsername(String principal) {
        User user = (User) userDetailsService.loadUserByUsername(principal);
        return userMapper.toFormDto(user);
    }

    @Override
    public List<UserFormDTO> findAllFormDTO() {
        return userRepository.findAll().stream().map(userMapper::toFormDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(user -> userMapper.toFullDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        return userMapper.toFullDto(userRepository.findOne(id));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDTO register(UserDTO userdto, boolean isAdmin){

        User user = userMapper.toEntity(userdto);

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

        return userMapper.toFullDto(userRepository.save(user));
    }

    private Authority createOrGetAuthority(String authority) {

        Authority found = authorityRepository.findByAuthority(authority);

        if (found == null) {
            found = new Authority(authority);
            authorityRepository.save(found);
        }

        return found;
    }

    @Override
    public UserDTO update(UserDTO userdto) {
        return userMapper.toFullDto(userRepository.save(userMapper.toEntity(userdto)));
    }

    @Override
    public void delete(UserDTO user) {
        user.enabled = false;
        userRepository.save(userMapper.toEntity(user));
    }

//    private List<UserDTO> toDtoList(List<User> users) {
//
//        List<UserDTO> dtoList = new ArrayList<>();
//
//        users.forEach((u) -> dtoList.add(u.toDto()));
//        return dtoList;
//    }
}
