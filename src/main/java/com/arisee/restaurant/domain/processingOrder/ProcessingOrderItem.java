package com.arisee.restaurant.domain.processingOrder;

import com.arisee.restaurant.model.processingOrder.ProcessingOrderItemForm;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@javax.persistence.Table(name = "processing_order_item")
public class ProcessingOrderItem {
   @EmbeddedId
    private ProcessingOrderItemPK pk = new ProcessingOrderItemPK();
    private String description;
    private Integer quantity;
    @Enumerated(EnumType.ORDINAL)
    private ProcessingOrderItemStatus status;



    public ProcessingOrderItemForm toProcessingForm(){
        ProcessingOrderItemForm rs = new ProcessingOrderItemForm();
        rs.setDescription(description);
        rs.setQuantity(quantity);
        rs.setStatus(status);
        return rs;
    }


}
