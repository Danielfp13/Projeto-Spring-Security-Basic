package com.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping
    public String welcome() {
        return "Welcome to My Spring Boot Web API";
    }

    @GetMapping("/users")
    //@PreAuthorize("hasAnyRole('USER')")
    public String users() {
        return "Authorized user";
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admins")
    public String admin() {
        return "Authorized admin";
    }

    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/usersandadmins")
    public String userAndAdmin() {
        return "Authorized admin and user.";
    }
}