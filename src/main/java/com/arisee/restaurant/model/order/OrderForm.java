package com.arisee.restaurant.model.order;


import com.arisee.restaurant.domain.order.OrderStatus;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderForm {

    @NotBlank
    private String customerName;

    @NotBlank
    private String phone;
    @NotNull
    private OrderStatus status;

    @NotEmpty
    private List<OrderItemForm> items;
    @NotNull
    private BigInteger tableId;
    @NotNull
    private BigInteger userId;
}
