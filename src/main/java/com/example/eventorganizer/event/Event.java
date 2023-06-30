package com.example.eventorganizer.event;

import com.example.eventorganizer.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private User admin;
    @ManyToMany
    private Set<User> users = new HashSet<>();
    public Event(String name,String description,User admin){
        this.name = name;
        this.description = description;
        this.admin = admin;
    }

    public Event(){}

}
