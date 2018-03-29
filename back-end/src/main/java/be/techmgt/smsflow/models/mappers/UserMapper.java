package be.techmgt.smsflow.models.mappers;

import be.techmgt.smsflow.models.dto.UserFormDTO;
import be.techmgt.smsflow.models.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserFormDTO toFormDto(User user){

        UserFormDTO userFormDTO = new UserFormDTO();

        userFormDTO.username = user.getUsername();
        userFormDTO.password = user.getPassword();
        userFormDTO.authorities = user.getAuthorities().stream()
                .map(auth -> auth.getAuthority()).collect(Collectors.toList());

        return userFormDTO;
    }
}
