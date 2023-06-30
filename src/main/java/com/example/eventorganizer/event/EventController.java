package com.example.eventorganizer.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class EventController {
    private final EventService eventService;
    private EventRepository eventRepository;
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
}
