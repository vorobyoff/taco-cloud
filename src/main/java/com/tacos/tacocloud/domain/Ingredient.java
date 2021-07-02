package com.tacos.tacocloud.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

import static java.util.Objects.hash;

@Entity
public class Ingredient {

    @Id
    private String id;
    private Type type;
    private String name;

    public Ingredient(final String id, final String name, final Type type) {
        this.name = name;
        this.type = type;
        this.id = id;
    }

    protected Ingredient() {
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ingredient that = (Ingredient) obj;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return hash(id);
    }

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}