package com.example.eventorganizer.task;

import com.example.eventorganizer.event.Event;
import com.example.eventorganizer.event.EventRepository;
import com.example.eventorganizer.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskServiceTest {
    @Mock
    private EventRepository eventRepository;
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void createTaskTest(){
        Long eventId = 1L;
        Event event = new Event("Event1","description",new User());

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        TaskDTO dto = new TaskDTO("task1",TaskState.UNASSIGNED);
        Task task = taskService.create(dto,eventId);

        verify(taskRepository).save(task);
        verify(eventRepository).save(event);

        assertEquals("task1",task.getName());
        assertEquals(TaskState.UNASSIGNED,task.getState());
    }
}