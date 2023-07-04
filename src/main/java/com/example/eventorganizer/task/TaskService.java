package com.example.eventorganizer.task;

import com.example.eventorganizer.event.Event;
import com.example.eventorganizer.event.EventRepository;
import com.example.eventorganizer.exceptions.EventNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private final EventRepository eventRepository;

    public Task create(TaskDTO dto,Long eventId){
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found."));
        Task task = new Task(dto.getName(),dto.getState(),event);
        event.addTask(task);
        eventRepository.save(event);
        taskRepository.save(task);
        return task;
    }
}
