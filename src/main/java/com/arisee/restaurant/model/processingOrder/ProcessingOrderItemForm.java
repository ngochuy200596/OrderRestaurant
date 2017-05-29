package com.arisee.restaurant.model.processingOrder;


import com.arisee.restaurant.domain.Dish.Dish;
import com.arisee.restaurant.domain.processingOrder.*;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class ProcessingOrderItemForm {
    @NotNull
    private Integer id;
    private BigInteger tableId;
    //    @NotBlank
    private String description;
    @NotNull
    private Integer quantity;
    @NotNull
    private ProcessingOrderItemStatus status;
    @NotNull
    private com.arisee.restaurant.model.dish.Dish dish;
}
