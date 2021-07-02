package com.tacos.tacocloud.controllers;

import com.tacos.tacocloud.domain.RegistrationForm;
import com.tacos.tacocloud.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public RegistrationController(final UserRepository repository, final PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping
    public final String registerForm() {
        return "registration";
    }

    @PostMapping
    public final String register(final RegistrationForm form) {
        repository.save(form.toUser(encoder));
        return "redirect:login";
    }
}