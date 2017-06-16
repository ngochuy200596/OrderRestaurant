package com.arisee.restaurant.model.processingOrder;

import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItemStatus;
import com.arisee.restaurant.model.dish.Dish;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class ProcessingOrderItem {
    private Integer id;
    private String description;
    private BigDecimal quantity;
    private ProcessingOrderItemStatus status;
    private BigInteger dishId;
}
