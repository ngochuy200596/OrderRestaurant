package com.arisee.restaurant.repository;


import com.arisee.restaurant.domain.Dish.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface DishRepository extends CrudRepository<Dish,BigInteger> {
    Optional<Dish> getById(BigInteger id);

    @Query("SELECT d FROM #{#entityName} d WHERE d.categoryId = ?1 ORDER BY d.rank DESC")
    Optional<List<Dish>> findByCategoryId(BigInteger categoryId);
}
