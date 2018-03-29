package be.techmgt.smsflow.models.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Authority extends BaseEntity implements GrantedAuthority {

    public static final String ADMIN = "ADMIN";
    public static final String CLIENT = "CLIENT";

    @Column(nullable = false, unique = true)
    private String authority;

    public Authority() {}

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
