package com.example.eventorganizer.task;

import com.example.eventorganizer.event.Event;
import com.example.eventorganizer.event.EventRepository;
import com.example.eventorganizer.exceptions.EventNotFoundException;
import com.example.eventorganizer.user.User;
import com.example.eventorganizer.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public Task create(TaskDTO dto,Long eventId){
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found."));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email);

        Task task = new Task(dto.getName(),dto.getState(),event,user,dto.getSpentMoney());
        event.addTask(task);
        eventRepository.save(event);
        taskRepository.save(task);
        return task;
    }
}
