package com.tacos.tacocloud.controllers;

import com.tacos.tacocloud.domain.Ingredient.Type;
import com.tacos.tacocloud.domain.Order;
import com.tacos.tacocloud.domain.Taco;
import com.tacos.tacocloud.repositories.IngredientRepository;
import com.tacos.tacocloud.repositories.TacoRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final Logger log = getLogger(DesignTacoController.class);
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    public DesignTacoController(final IngredientRepository ingredientRepository, final TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public final String showDesignMode(final Model model) {
        final var data = ingredientRepository.findAll();
        final var ingredients = convertIterableToList(data);

        for (final var type : Type.values()) {
            final var filtered = ingredients.stream().filter(i -> i.getType() == type).collect(toList());
            model.addAttribute(type.toString().toLowerCase(), filtered);
        }

        return "design";
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @PostMapping
    public final String processDesign(@Valid final Taco taco, @ModelAttribute(name = "order") final Order order, final Errors errors) {
        if (errors.hasErrors()) {
            log(errors);
            return "design";
        }

        final var saved = tacoRepository.save(taco);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    private <T> List<T> convertIterableToList(final Iterable<T> iterable) {
        if (iterable == null) return emptyList();

        final var iterator = iterable.iterator();
        final var list = new ArrayList<T>();
        while (iterator.hasNext())
            list.add(iterator.next());

        return list;
    }

    private void log(final Errors errors) {
        for (final var e : errors.getAllErrors())
            log.error(e.toString());
    }
}