package com.arisee.restaurant.controller;
import com.arisee.restaurant.domain.category.Category;
import com.arisee.restaurant.exception.NotFoundException;
import com.arisee.restaurant.model.category.CategoryForm;
import com.arisee.restaurant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public List<com.arisee.restaurant.model.category.Category> getAll(){
        return this.categoryService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Category getById(@PathVariable("id") BigInteger id){
        return this.categoryService.getById(id).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id){
        this.categoryService.delete(id);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category update(@PathVariable("id") BigInteger id,@Valid @RequestBody CategoryForm categoryForm){
        return this.categoryService.update(id,categoryForm).orElseThrow(NotFoundException :: new);
    }
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category inset(@Valid @RequestBody CategoryForm categoryForm){
        return this.categoryService.create(categoryForm);
    }

}
