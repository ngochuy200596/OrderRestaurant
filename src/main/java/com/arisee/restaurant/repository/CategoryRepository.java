package com.arisee.restaurant.repository;


import com.arisee.restaurant.domain.category.Category;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,BigInteger>{
    Optional<Category> getById(BigInteger id);
}
