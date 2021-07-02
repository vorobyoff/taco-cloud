package com.tacos.tacocloud.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

import static java.util.Collections.singletonList;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "user")
public class User implements UserDetails {

    private static final String ROLE = "USER_ROLE";
    @Column(name = "username",
            nullable = false,
            unique = true)
    private final String username;
    @Column(name = "password",
            nullable = false)
    private final String password;
    @Column(name = "full_name",
            nullable = false)
    private final String fullName;
    @Column(name = "street")
    private final String street;
    @Column(name = "city")
    private final String city;
    @Column(name = "state")
    private final String state;
    @Column(name = "zip")
    private final String zip;
    @Column(name = "phone_number",
            nullable = false,
            unique = true)
    private final String phoneNumber;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id",
            updatable = false,
            nullable = false,
            unique = true)
    private Long id;

    public User(final String username, final String password, final String fullName, final String street,
                final String city, final String state, final String zip, final String phoneNumber) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
    }

    @Deprecated
    // Using only for JPA
    protected User() {
        this(null, null, null, null, null, null, null, null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return singletonList(new SimpleGrantedAuthority(ROLE));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}