package com.arisee.restaurant.service;

import com.arisee.restaurant.domain.category.Category;
import com.arisee.restaurant.model.category.CategoryForm;
import com.arisee.restaurant.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService{
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<com.arisee.restaurant.model.category.Category> getAll(){
        List<com.arisee.restaurant.model.category.Category> rs = new ArrayList<>();
        this.categoryRepository.findAll().forEach(category -> {
            rs.add(category.toCategory());
        });
        return rs;
    }

    public Optional<Category> getById(BigInteger id){
        return this.categoryRepository.getById(id);
    }

    public void delete(BigInteger id){
        getById(id).ifPresent(this.categoryRepository::delete);
    }

    public Optional<Category> update(BigInteger id, CategoryForm categoryForm){
        return getById(id).map(category -> {
            category.setName(categoryForm.getName());
            return this.categoryRepository.save(category);
        });
    }

    public Category create(CategoryForm categoryForm){
        Category category = new Category();
        category.setName(categoryForm.getName());
        return this.categoryRepository.save(category);
    }
}
