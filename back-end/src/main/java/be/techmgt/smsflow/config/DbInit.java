package be.techmgt.smsflow.config;

import be.techmgt.smsflow.models.dto.UserDTO;
import be.techmgt.smsflow.models.entity.Authority;
import be.techmgt.smsflow.models.entity.User;
import be.techmgt.smsflow.services.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DbInit implements InitializingBean {

    private final UserService userService;

    public DbInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        runAsAdmin();

        if (userService.findByUsername("adminC") == null) {

            UserDTO userdto = new UserDTO();
            userdto.companyName = "TechMGT";
            userdto.nameOfContact = "Christopher Delfosse";
            userdto.email = "christopher@techmgt.be";
            userdto.username = "adminC";
            userdto.password = "admin";
            userdto.phoneNumber = "0474247112";
            userdto.street = "Rue Abel Wart, 198";
            userdto.city = "Fayt-Lez-Manage";
            userdto.postalCode = "7170";
            userdto.country = "Belgium";
            userdto.tvaNumber = "BE758425948";

            userService.register(userdto, true);
        }

        endRunAsAdmin();
    }

    private void runAsAdmin() {
        final AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("system", "system", Collections.singletonList(new Authority("ROLE_ADMIN")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    private void endRunAsAdmin() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
