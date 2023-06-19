package com.example.eventorganizer.task;

import com.example.eventorganizer.event.Event;
import com.example.eventorganizer.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Event event;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private TaskState state;

    public Task(String name,TaskState state){
        this.name = name;
        this.state = state;
    }
    public Task(){}
}
