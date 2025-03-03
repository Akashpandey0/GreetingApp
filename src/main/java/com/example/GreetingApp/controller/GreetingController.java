package com.example.GreetingApp.controller;

import com.example.GreetingApp.model.Greeting;
import com.example.GreetingApp.service.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // Fetch Greeting by ID
    @GetMapping("/{id}")
    public Greeting getGreetingById(@PathVariable Long id) {
        return greetingService.getGreetingById(id);
    }

    // ✅ Fix: Add this method for POST request
    @PostMapping("/save")
    public ResponseEntity<Greeting> saveGreeting(@RequestBody Greeting greeting) {
        Greeting savedGreeting = greetingService.saveGreeting(greeting);
        return ResponseEntity.ok(savedGreeting);
    }
}