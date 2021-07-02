package com.tacos.tacocloud.controllers;

import com.tacos.tacocloud.domain.Order;
import com.tacos.tacocloud.repositories.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("order")
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/current")
    public final String orderForm() {
        return "order";
    }

    @PostMapping
    public final String processOrder(@Valid Order order, Errors errors, SessionStatus status) {
        if (errors.hasErrors()) return "order";
        repository.save(order);
        status.setComplete();
        return "redirect:/";
    }
}
