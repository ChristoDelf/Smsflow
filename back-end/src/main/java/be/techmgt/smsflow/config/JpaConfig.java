package be.techmgt.smsflow.config;

import be.techmgt.smsflow.models.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {

        return new AuditorAware<String>() {

            @Override
            public String getCurrentAuditor() {

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                if (authentication == null || !authentication.isAuthenticated()) {
                    return null;
                }

                Object principal = authentication.getPrincipal();
                if (principal instanceof User) {
                    return ((User) principal).getUsername();
                } else if (principal instanceof String) {
                    return (String) principal;
                }
                return null;
            }
        };
    }
}
