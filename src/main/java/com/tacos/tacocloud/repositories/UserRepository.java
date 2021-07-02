package com.tacos.tacocloud.repositories;

import com.tacos.tacocloud.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByUsername(final String username);
}