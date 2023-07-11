package com.example.eventorganizer.task;

import com.example.eventorganizer.event.Event;
import com.example.eventorganizer.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

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
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="task_payment_details")
    @MapKeyJoinColumn(name= "user_id")
    @Column(name= "contribution")
    private Map<User, BigDecimal> paymentDetails;

    public Task(String name,TaskState state,Event event,User user,BigDecimal spentMoney){
        this.name = name;
        this.state = state;
        this.event = event;
        this.user=user;
        this.spentMoney = spentMoney;
    }
    public Task(){}
}
