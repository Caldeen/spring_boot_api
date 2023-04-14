package main.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Documented;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
