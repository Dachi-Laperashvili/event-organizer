package com.example.eventorganizer.user;

import com.example.eventorganizer.event.Event;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @ManyToMany(mappedBy = "users")
    private Set<Event> events = new HashSet<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {}
}
