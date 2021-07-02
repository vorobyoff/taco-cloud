package com.tacos.tacocloud.repositories;

import com.tacos.tacocloud.domain.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}