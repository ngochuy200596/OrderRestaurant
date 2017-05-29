package com.arisee.restaurant.domain.processingOrder;

import com.arisee.restaurant.domain.Dish.Dish;
import com.arisee.restaurant.model.processingOrder.ProcessingOrderItemForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@javax.persistence.Table(name = "processing_order_item")
public class ProcessingOrderItem {
    @EmbeddedId
    private ProcessingOrderItemPK pk = new ProcessingOrderItemPK();

    @Enumerated(EnumType.ORDINAL)
    private ProcessingOrderItemStatus status;
    private String description;
    private Integer quantity;



    @OneToOne
    @JoinColumn(name = "dishId")
    private Dish dish;

    public ProcessingOrderItemForm toProcessingForm() {
        ProcessingOrderItemForm rs = new ProcessingOrderItemForm();
        rs.setDescription(description);
        rs.setQuantity(quantity);
        rs.setStatus(status);
        rs.setId(pk.getId());
        rs.setDish(dish.toDish());
        return rs;
    }

    public com.arisee.restaurant.model.processingOrder.ProcessingOrderItem toProcessingOrderItem(){
        com.arisee.restaurant.model.processingOrder.ProcessingOrderItem rs = new com.arisee.restaurant.model.processingOrder.ProcessingOrderItem();
        rs.setDishId(dish.getId());
        rs.setId(pk.getId());
        rs.setDescription(description);
        rs.setQuantity(quantity);
        rs.setStatus(status);
        return rs;
    }
}
