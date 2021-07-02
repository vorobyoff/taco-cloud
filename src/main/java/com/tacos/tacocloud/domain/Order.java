package com.tacos.tacocloud.domain;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.hash;

@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    @NotBlank(message = "Name is required.")
    private String name;
    @NotBlank(message = "Street is required.")
    private String street;
    @NotBlank(message = "City is required.")
    private String city;
    @NotBlank(message = "State is required.")
    private String state;
    @NotBlank(message = "Zip code is required.")
    private String zip;
    @CreditCardNumber(message = "Not a valid card number.")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([/])([1-9][0-9])$",
            message = "Must be formatted MM/YY.")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV.")
    private String ccCVV;
    private LocalDateTime placedAt;
    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos;

    public Order(final Long id, @NotBlank(message = "Name is required.") final String name,
                 @NotBlank(message = "Street is required.") final String street,
                 @NotBlank(message = "City is required.") final String city,
                 @NotBlank(message = "State is required.") final String state,
                 @NotBlank(message = "Zip code is required.") final String zip,
                 @CreditCardNumber(message = "Not a valid card number.") final String ccNumber,
                 @Pattern(regexp = "^(0[1-9]|1[0-2])([/])([1-9][0-9])$", message = "Must be formatted MM/YY.") final String ccExpiration,
                 @Digits(integer = 3, fraction = 0, message = "Invalid CVV.") final String ccCVV,
                 final LocalDateTime placedAt, final List<Taco> tacos) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.ccNumber = ccNumber;
        this.ccExpiration = ccExpiration;
        this.ccCVV = ccCVV;
        this.placedAt = placedAt;
        this.tacos = tacos;
    }

    public Order() {
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

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(final String zip) {
        this.zip = zip;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(final String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(final String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(final String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(final LocalDateTime placedAt) {
        this.placedAt = placedAt;
    }

    @PrePersist
    void placeAt() {
        placedAt = LocalDateTime.now();
    }

    public List<Taco> getTacos() {
        return unmodifiableList(tacos);
    }

    public void setTacos(final List<Taco> tacos) {
        this.tacos = tacos;
    }

    public void addDesign(final Taco design) {
        tacos.add(design);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;

        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return hash(id);
    }
}