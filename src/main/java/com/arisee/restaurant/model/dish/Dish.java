package com.arisee.restaurant.model.dish;


import com.arisee.restaurant.domain.Dish.DishStatus;
import lombok.Data;

import java.math.BigInteger;
@Data
public class Dish {
    private BigInteger id;
    private String name;
    private DishStatus status;
    private float price;
    private BigInteger categoryId;
    private String image;
}
