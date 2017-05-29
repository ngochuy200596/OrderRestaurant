package com.arisee.restaurant.controller;


import com.arisee.restaurant.domain.Dish.Dish;
import com.arisee.restaurant.domain.Dish.DishStatus;
import com.arisee.restaurant.exception.NotFoundException;
import com.arisee.restaurant.model.dish.DishForm;
import com.arisee.restaurant.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;


@RestController
@RequestMapping("/api/dishes")
public class DishController {
    @Autowired
    private DishService dishService;

    @RequestMapping(method = RequestMethod.GET)
    public List<com.arisee.restaurant.model.dish.Dish> getAll() {
        return this.dishService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Dish getById(@PathVariable("id") BigInteger id) {
        return this.dishService.getById(id).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id) {
        this.dishService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish update(@PathVariable("id") BigInteger id, @Valid @RequestBody DishForm dishForm) {
        return this.dishService.update(id, dishForm).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.POST)
    public Dish setStatus(@PathVariable("id") BigInteger id, @PathVariable Integer status) {
        return this.dishService.setStatus(id,status).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish insert(@Valid @RequestBody DishForm dishForm) {
        return this.dishService.create(dishForm);
    }
}
