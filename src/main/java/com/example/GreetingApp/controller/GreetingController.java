package com.example.GreetingApp.controller;

import com.example.greeting_app.model.Greeting;
import com.example.greeting_app.service.GreetingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping("/save")
    public Greeting saveGreeting(@RequestParam String message) {
        return greetingService.saveGreeting(message);
    }
}