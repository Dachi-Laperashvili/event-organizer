package com.example.eventorganizer.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/home/createEvent",path="/home/createEvent")
public class EventController {
    private final EventService eventService;
    @GetMapping
    public String createEvent(){
        return "createEvent";
    }
    @PostMapping
    public String createEvent(@RequestParam("name") String name,
                             @RequestParam("description") String description){

        EventDTO dto = new EventDTO(name, description);
        eventService.create(dto);
        return "redirect:/home";
    }
}
