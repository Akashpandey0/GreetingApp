package com.example.GreetingApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF hata diya (H2 Console ke liye zaroori)
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // H2 Console ke liye frameOptions disable
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // H2 Console ko allow kiya
                        .requestMatchers("/api/auth/**").permitAll() // Register/Login ko allow kiya
                        .anyRequest().authenticated() // Baaki sab ke liye authentication
                )
                .formLogin(Customizer.withDefaults()) // Default Login form enable
                .httpBasic(Customizer.withDefaults()); // Postman ke liye Basic Authentication

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}