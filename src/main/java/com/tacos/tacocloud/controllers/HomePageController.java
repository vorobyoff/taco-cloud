package com.tacos.tacocloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public final String home() {
        return "home";
    }
}