package com.example.eventorganizer;

import jakarta.persistence.*;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public User() {}
}
