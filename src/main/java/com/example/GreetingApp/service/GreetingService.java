package com.example.GreetingApp.service;

import com.example.GreetingApp.model.Greeting;
import com.example.GreetingApp.repository.GreetingRepository;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting getGreetingById(Long id) {
        return greetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Greeting not found with ID: " + id));
    }

    // ✅ Fix: Save Greeting to Database
    public Greeting saveGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);  // This ensures data is saved in the database
    }
}