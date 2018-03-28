package be.techmgt.smsflow.models.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Template extends BaseEntity {

    private String name;
    private String message;

    @ManyToOne
    @JoinColumn(name="idUser")
    private User owner;

    public Template() {
    }

    public Template(String templateName, String message, User owner) {
        this.name = templateName;
        this.message = message;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String templateName) {
        this.name = templateName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


}
