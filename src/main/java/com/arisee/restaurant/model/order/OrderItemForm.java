package com.arisee.restaurant.model.order;


import com.arisee.restaurant.domain.Dish.Dish;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class OrderItemForm {
//    @NotNull
//    private BigInteger dishId;
    @NotNull
    private Dish dish;
//    @NotBlank
    private String description;
    @NotNull
    private BigDecimal quantity;
}
