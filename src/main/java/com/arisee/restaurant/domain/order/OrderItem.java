package com.arisee.restaurant.domain.order;


import com.arisee.restaurant.model.dish.Dish;
import com.arisee.restaurant.model.order.OrderItemForm;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "order_item")
@Data
public class OrderItem {

    @EmbeddedId
    private OrderItemPK pk = new OrderItemPK();
    private String description;
    private BigDecimal quantity;
//    private BigInteger dishId;

    @OneToOne
    @JoinColumn(name = "dishId")
    private com.arisee.restaurant.domain.Dish.Dish dish;


    public OrderItemForm toForm() {
        OrderItemForm rs = new OrderItemForm();
        rs.setDescription(description);
        rs.setQuantity(quantity);
        rs.setDish(dish);
        return rs;
    }

}
