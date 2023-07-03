package com.example.eventorganizer.event;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.eventorganizer.exceptions.EventNotFoundException;
import com.example.eventorganizer.exceptions.UserNotFoundException;
import com.example.eventorganizer.user.User;
import com.example.eventorganizer.user.UserRepository;
import com.example.eventorganizer.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class EventServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EventRepository eventRepository;
    @InjectMocks
    private EventService eventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEvent() {
        String email = "john.doe@gmail.com";

        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn(email);

        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(user);

        EventDTO dto = new EventDTO("Pizza Party", "birthday event");

        Event event = eventService.create(dto);

        verify(eventRepository).save(event);

        assertEquals("Pizza Party", event.getName());
        assertEquals("birthday event", event.getDescription());
        assertEquals(user, event.getAdmin());

        verify(userRepository).findByEmail(email);
    }
    @Test
    public void testEventNotFoundException(){
        String errorMessage = "Only admin can add users to the event.";
        EventNotFoundException eventNotFoundException = new EventNotFoundException(errorMessage);

        assertEquals(errorMessage,eventNotFoundException.getMessage());
    }
    @Test
    public void testUserNotFoundException(){
        String errorMessage = "User not found.";
        UserNotFoundException userNotFoundException = new UserNotFoundException(errorMessage);

        assertEquals(errorMessage,userNotFoundException.getMessage());
    }
    @Test
    public void testAddUserWhenNonAdminTriesIt(){
        Long userId = 1L;
        Long eventId = 1L;
        String nonAdminEmail = "example@gmail.com";
        User nonAdmin = new User("Non","Admin","example@gmail.com","password",UserRole.USER);
        User Admin = new User("Admin","User","admin@gmail.com","adminPassword",UserRole.ADMIN);
        Event event = new Event("Event","first event",Admin);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        when(userRepository.findById(userId)).thenReturn(Optional.of(nonAdmin));
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(authentication.getName()).thenReturn(nonAdminEmail);

        String errorMessage = eventService.addUser(userId, eventId);

        assertEquals("Only admin can add users to the event.", errorMessage);
    }
}