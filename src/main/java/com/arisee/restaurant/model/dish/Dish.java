package com.arisee.restaurant.model.dish;


import com.arisee.restaurant.domain.Dish.DishStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
@Data
public class Dish {
    private BigInteger id;
    private String name;
    private DishStatus status;
    private BigDecimal price;
    private BigInteger categoryId;
    private String image;
    private BigInteger rank;
}
