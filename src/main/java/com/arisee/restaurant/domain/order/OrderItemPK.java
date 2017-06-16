package com.arisee.restaurant.domain.order;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Data
@Embeddable
public class OrderItemPK implements Serializable{
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @Override
    public String toString() {
        return "OrderItemPK{" +
                "id=" + id +
                '}';
    }
}

