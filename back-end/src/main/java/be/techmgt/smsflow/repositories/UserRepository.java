package be.techmgt.smsflow.repositories;

import be.techmgt.smsflow.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
