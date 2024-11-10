package org.interaction.interactionbackend.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.interaction.interactionbackend.enums.Role;

/**
 * the basic information of user
 */
@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private Role role;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {}
}