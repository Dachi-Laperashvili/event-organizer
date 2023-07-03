package com.example.eventorganizer.event;

import com.example.eventorganizer.exceptions.UserNotFoundException;
import com.example.eventorganizer.user.User;
import com.example.eventorganizer.user.UserRepository;
import com.example.eventorganizer.exceptions.EventNotFoundException;
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
    public String addUser(Long userId, Long eventId){
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found."));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found."));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User admin = userRepository.findByFirstName(username);

        if (admin == null || event.getAdmin() == null) {
            return "Only admin can add users to the event.";
        }

        if(!event.getAdmin().getId().equals(admin.getId())) {
            return "Only admin can add users to the event.";
        }
        event.addUser(user);
        eventRepository.save(event);
        return "successfully added user";
    }
}
