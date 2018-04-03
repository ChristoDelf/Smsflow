package be.techmgt.smsflow.models.mappers;

import be.techmgt.smsflow.models.dto.UserDTO;
import be.techmgt.smsflow.models.dto.UserFormDTO;
import be.techmgt.smsflow.models.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserFormDTO toFormDto(User user) {

        UserFormDTO userFormDTO = new UserFormDTO();

        userFormDTO.username = user.getUsername();
        userFormDTO.password = user.getPassword();
        userFormDTO.authorities = user.getAuthorities().stream()
                .map(auth -> auth.getAuthority()).collect(Collectors.toList());

        return userFormDTO;
    }

    public UserDTO toFullDto(User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.companyName = user.getCompanyName();
        userDTO.nameOfContact = user.getNameOfContact();
        userDTO.username = user.getUsername();
        userDTO.password = null;
        userDTO.email = user.getEmail();
        userDTO.creditsLeft = user.getCreditsLeft();
        userDTO.creditsTotal = user.getCreditsTotal();

        userDTO.authorities =  user.getAuthorities().stream()
                .map(auth -> auth.getAuthority()).collect(Collectors.toList());

        userDTO.city = user.getCity();
        userDTO.country = user.getCountry();
        userDTO.street = user.getStreet();
        userDTO.phoneNumber = user.getPhoneNumber();
        userDTO.postalCode = user.getPostalCode();
        userDTO.tvaNumber = user.getTvaNumber();

        return userDTO;
    }

    public User toEntity(UserDTO userdto) {

        User user = new User();

        user.setCompanyName(userdto.companyName);
        user.setNameOfContact(userdto.nameOfContact);
        user.setUsername(userdto.username);
        user.setPassword(userdto.password);
        user.setEmail(userdto.email);
        user.setCreditsLeft(userdto.creditsLeft);
        user.setCreditsTotal(userdto.creditsTotal);

        user.setStreet(userdto.street);
        user.setCity(userdto.city);
        user.setCountry(userdto.country);
        user.setPhoneNumber(userdto.phoneNumber);
        user.setTvaNumber(userdto.tvaNumber);
        user.setPostalCode(userdto.postalCode);

        user.setContacts(new ArrayList<>());

        return user;
    }
}
