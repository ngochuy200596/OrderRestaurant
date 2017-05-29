package com.arisee.restaurant.domain.processingOrder;

import com.arisee.restaurant.domain.Dish.Dish;
import com.arisee.restaurant.domain.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable

public class ProcessingOrderItemPK implements Serializable {
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "tableId", nullable = false)
    @JsonIgnore
    private TableProcessingOrder tableProcessingOrder;


    @Override
    public String toString() {
        return "ProcessingOrderItemPK{" +
                "id=" + id +
                '}';
    }
}
