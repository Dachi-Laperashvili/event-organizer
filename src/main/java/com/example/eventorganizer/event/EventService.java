package com.example.eventorganizer.event;

import com.example.eventorganizer.user.User;
import com.example.eventorganizer.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventService {
    private EventRepository eventRepository;
    private UserRepository userRepository;
    public Event create(EventDTO dto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByFirstName(username);
        Event event = new Event(
                dto.getName(),
                dto.getDescription(),
                user);

        eventRepository.save(event);
        return event;
    }
}
