package com.example.eventorganizer.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.eventorganizer.user.User;
import com.example.eventorganizer.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
        String username = "John";

        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn(username);

        User user = new User();
        user.setFirstName(username);
        when(userRepository.findByFirstName(username)).thenReturn(user);

        EventDTO dto = new EventDTO("Pizza Party", "birthday event");

        Event event = eventService.create(dto);

        verify(eventRepository).save(event);

        assertEquals("Pizza Party", event.getName());
        assertEquals("birthday event", event.getDescription());
        assertEquals(user, event.getAdmin());

        verify(userRepository).findByFirstName(username);
    }
}