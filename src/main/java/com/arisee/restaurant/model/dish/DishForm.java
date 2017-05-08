package com.arisee.restaurant.model.dish;

import com.arisee.restaurant.domain.Dish.DishStatus;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class DishForm {
    @NotBlank
    private String name;
    @NotNull
    private DishStatus status;
    private float price;
    @NotNull
    private BigInteger categoryId;
}
