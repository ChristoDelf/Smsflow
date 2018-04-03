package be.techmgt.smsflow.models.dto;

import be.techmgt.smsflow.models.entity.Contact;

import java.util.List;

public class UserDTO {

    public Long id;
    public String companyName;
    public String nameOfContact;
    public String email;
    public String username;
    public String password;
    public String phoneNumber;
    public String street;
    public String city;
    public String postalCode;
    public String country;
    public String tvaNumber;
    public int creditsTotal;
    public int creditsLeft;

    public List<String> authorities;

    public List<Contact> contacts;

    public boolean enabled;

}
