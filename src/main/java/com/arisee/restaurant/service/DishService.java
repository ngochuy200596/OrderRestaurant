package com.arisee.restaurant.service;

import com.arisee.restaurant.domain.Dish.Dish;
import com.arisee.restaurant.domain.Dish.DishStatus;
import com.arisee.restaurant.model.dish.DishForm;
import com.arisee.restaurant.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DishService {
    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<com.arisee.restaurant.model.dish.Dish> getAll() {
        return StreamSupport.stream(this.dishRepository.findAll().spliterator(), false)
                .map(Dish::toDish)
                .collect(Collectors.toList());
    }

    public Optional<Dish> getById(BigInteger id) {
        return this.dishRepository.getById(id);
    }

    public void delete(BigInteger id) {
        getById(id).ifPresent(dishRepository::delete);
    }

    public Optional<Dish> update(BigInteger id, DishForm dishForm) {
        return getById(id).map(dish -> {
            dish.setName(dishForm.getName());
            dish.setStatus(dishForm.getStatus());
            dish.setPrice(dishForm.getPrice());
            dish.setCategoryId(dishForm.getCategoryId());
            return this.dishRepository.save(dish);
        });
    }

    public Optional<Dish> setStatus(BigInteger id, Integer status) {
        return getById(id).map(dish -> {
            dish.setStatus(DishStatus.values()[status]);
            return this.dishRepository.save(dish);
        });
    }

    public Dish create(DishForm dishForm) {
        Dish dish = new Dish();
        dish.setName(dishForm.getName());
        dish.setStatus(dishForm.getStatus());
        dish.setPrice(dishForm.getPrice());
        dish.setCategoryId(dishForm.getCategoryId());
        return this.dishRepository.save(dish);
    }
}
