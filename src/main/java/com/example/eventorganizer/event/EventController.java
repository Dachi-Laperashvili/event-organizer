package com.example.eventorganizer.event;

import com.example.eventorganizer.exceptions.EventNotFoundException;
import com.example.eventorganizer.task.Task;
import com.example.eventorganizer.task.TaskRepository;
import com.example.eventorganizer.user.User;
import com.example.eventorganizer.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class EventController {
    private EventService eventService;
    private EventRepository eventRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @RequestMapping(value = "/home/createEvent")
    public String createEvent(){
        return "createEvent";
    }
    @PostMapping(path="/home/createEvent")
    public String createEvent(@RequestParam("name") String name,
                              @RequestParam("description") String description){

        EventDTO dto = new EventDTO(name, description);
        eventService.create(dto);
        return "redirect:/home";
    }
    @GetMapping(path="/home/seeAll")
    public ModelAndView getAll(){
        ModelAndView mav = new ModelAndView("listEvents");
        mav.addObject("events",eventRepository.findAll());
        return mav;
    }

    @PostMapping(path="/home/inviteUser")
    public String inviteUser(@RequestParam("userId") Long userId,
                             @RequestParam("eventId") Long eventId,
                             Model model) {
        String errorMessage = eventService.addUser(userId, eventId);
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            return "createEvent";
        }
        return eventService.addUser(userId, eventId);
    }
    @RequestMapping("/event/{eventId}")
    public String showEvent(@PathVariable("eventId") Long eventId, Model model){
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event Not Found!"));

        List<Task> tasks = taskRepository.findByEvent(event);

        model.addAttribute("event",event);
        model.addAttribute("tasks",tasks);
        return "event"; // event.html file
    }
}
