package com.example.eventorganizer.task;

import com.example.eventorganizer.event.Event;
import com.example.eventorganizer.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tasks")
@Getter
@Setter
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
    private BigDecimal spentMoney;

    public Task(String name,TaskState state,Event event){
        this.name = name;
        this.state = state;
        this.event = event;
    }
    public Task(){}
}
