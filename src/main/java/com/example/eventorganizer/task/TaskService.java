package com.example.eventorganizer.task;

import com.example.eventorganizer.event.Event;
import com.example.eventorganizer.event.EventRepository;
import com.example.eventorganizer.exceptions.EventNotFoundException;
import com.example.eventorganizer.exceptions.TaskNotFoundException;
import com.example.eventorganizer.user.User;
import com.example.eventorganizer.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ContributionService contributionService;

    public Task create(TaskDTO dto,Long eventId){
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found."));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = null;


        if(dto.getState() != TaskState.UNASSIGNED) {
            user = userRepository.findByEmail(email);
        }

        Task task = new Task(dto.getName(),dto.getState(),event,user, BigDecimal.ZERO);
        event.addTask(task);

        eventRepository.save(event);
        taskRepository.save(task);
        return task;
    }
    public void update(Long taskId, TaskState newState, BigDecimal spentMoney){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not Found"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email);

        task.setState(newState);
        task.setSpentMoney(spentMoney);
        if(task.getUser() == null){
            task.setUser(user);
        }
        Map<User,BigDecimal> contributions = contributionService.calculateContributions(task.getEvent(),task.getUser(),task.getSpentMoney());
        task.setPaymentDetails(contributions);

        taskRepository.save(task);
    }
}
