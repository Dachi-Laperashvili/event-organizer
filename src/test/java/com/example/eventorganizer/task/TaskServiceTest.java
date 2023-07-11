package com.example.eventorganizer.task;

import com.example.eventorganizer.event.Event;
import com.example.eventorganizer.event.EventRepository;
import com.example.eventorganizer.user.User;
import com.example.eventorganizer.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    @Mock
    private EventRepository eventRepository;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserRepository userRepository;
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
        String email = "example@gmail.com";
        User user = new User();
        user.setEmail(email);

        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(authentication.getName()).thenReturn(email);

        TaskDTO dto = new TaskDTO("task1",TaskState.UNASSIGNED);
        Task task = taskService.create(dto,eventId);

        when(userRepository.findByEmail(email)).thenReturn(user);
        verify(taskRepository).save(task);
        verify(eventRepository).save(event);

        assertEquals("task1",task.getName());
        assertEquals(TaskState.UNASSIGNED,task.getState());
        assertEquals(BigDecimal.ZERO,task.getSpentMoney());
        assertNull(task.getUser());
    }
    @Test
    public void createTaskWhenStateIsNotUnassigned(){
        Long eventId = 1L;
        Event event = new Event("Event1", "description", new User());
        String email = "example@gmail.com";
        User user = new User();
        user.setEmail(email);

        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(authentication.getName()).thenReturn(email);

        TaskDTO dto = new TaskDTO("task1", TaskState.IN_PROGRESS);
        Task task = taskService.create(dto, eventId);

        verify(taskRepository).save(task);
        verify(eventRepository).save(event);

        assertEquals("task1", task.getName());
        assertEquals(TaskState.IN_PROGRESS, task.getState());
        assertEquals(BigDecimal.ZERO, task.getSpentMoney());
        assertEquals(user, task.getUser());
    }
}