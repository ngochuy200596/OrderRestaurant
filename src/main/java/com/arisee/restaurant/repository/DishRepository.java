package com.arisee.restaurant.repository;


import com.arisee.restaurant.domain.Dish.Dish;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface DishRepository extends CrudRepository<Dish,BigInteger> {
    Optional<Dish> getById(BigInteger id);
}
