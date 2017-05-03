package com.arisee.restaurant.domain.processingOrder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable

public class ProcessingOrderItemPK implements Serializable {
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "tableId")
    @JsonIgnore
    private TableProcessingOrder tableProcessingOrder;

    @Override
    public String toString() {
        return "ProcessingOrderItemPK{" +
                "id=" + id +
                '}';
    }
}
