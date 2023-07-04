package com.example.eventorganizer.event;

import com.example.eventorganizer.task.Task;
import com.example.eventorganizer.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private User admin;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();
    @OneToMany(mappedBy = "event",fetch = FetchType.EAGER)
    private Set<Task> tasks = new HashSet<>();
    public Event(String name,String description,User admin){
        this.name = name;
        this.description = description;
        this.admin = admin;
    }

    public Event(){}
    public void addUser(User user){
            users.add(user);
    }
    public void addTask(Task task){
        tasks.add(task);
    }
}
