package be.techmgt.smsflow.config;

import be.techmgt.smsflow.models.entity.User;
import be.techmgt.smsflow.services.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements InitializingBean {

    private final UserService userService;

    public DbInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (userService.findByUsername("adminC") == null) {

            User user = new User();
            user.setCompanyName("TechMGT");
            user.setNameOfContact("Christopher Delfosse");
            user.setEmail("christopher@techmgt.be");
            user.setUsername("adminC");
            user.setPassword("admin");
            user.setPhoneNumber("0474247112");
            user.setStreet("Rue Abel Wart, 198");
            user.setCity("Fayt-Lez-Manage");
            user.setPostalCode("7170");
            user.setCountry("Belgium");
            user.setTvaNumber("BE758425948");

            user = userService.register(user, true);
        }
    }
}
