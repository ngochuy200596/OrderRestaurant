package com.arisee.restaurant.model.category;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
@Data
public class CategoryForm {
    @NotBlank
    private String name;
}
