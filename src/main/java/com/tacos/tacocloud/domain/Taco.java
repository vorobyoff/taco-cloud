package com.tacos.tacocloud.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.hash;
import static javax.persistence.GenerationType.AUTO;

@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long.")
    private String name;
    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min = 1, message = "You must choose at least 1 ingredient.")
    private List<Ingredient> ingredients;
    private LocalDateTime createdAt;

    public Taco(final Long id, @NotNull @Size(min = 5, message = "Name must be at least 5 characters long.") String name,
                @Size(min = 1, message = "You must choose at least 1 ingredient.") List<Ingredient> ingredients,
                LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.ingredients = ingredients;
    }

    public Taco() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return unmodifiableList(ingredients);
    }

    public void setIngredients(final List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    void createdAt() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Taco taco = (Taco) obj;

        return Objects.equals(id, taco.id);
    }

    @Override
    public int hashCode() {
        return hash(id);
    }
}