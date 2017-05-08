package com.arisee.restaurant.model.order;

import com.arisee.restaurant.domain.order.OrderStatus;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private BigInteger id;
    private String customerName;
    private String phone;

    private OrderStatus status;
    private LocalDateTime scheduleOn;
    private LocalDateTime createdDate;
    private List<OrderItemForm> items;
    private BigInteger tableId;
    private BigInteger userId;
}
