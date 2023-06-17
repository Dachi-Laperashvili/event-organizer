package com.example.eventorganizer.event;

import com.example.eventorganizer.user.User;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private User admin;
    @ManyToMany
    private Set<User> users = new HashSet<>();
    public Event(String name){
        this.name = name;
    }

    public Event(){}

}
