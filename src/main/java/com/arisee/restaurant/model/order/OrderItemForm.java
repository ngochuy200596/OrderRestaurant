package com.arisee.restaurant.model.order;


import com.arisee.restaurant.domain.Dish.Dish;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class OrderItemForm {
    @NotNull
    private BigInteger dishId;
    @NotBlank
    private String description;
    @NotNull
    private Integer quantity;
}
