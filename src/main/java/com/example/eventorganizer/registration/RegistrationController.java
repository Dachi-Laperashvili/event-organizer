package com.example.eventorganizer.registration;

import com.example.eventorganizer.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/registration",path="/registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping
    public String registration(){
        return "registration";
    }
    @PostMapping
    public User register(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password){
        RegistrationRequest request = new RegistrationRequest(firstName, lastName, email, password);
        return registrationService.register(request);
    }
}
