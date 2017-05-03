package com.arisee.restaurant.model.processingOrder;


import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItemStatus;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class ProcessingOrderItemForm {
//    @NotNull
//    private BigInteger tableId;
    @NotBlank
    private String description;
    @NotNull
    private Integer quantity;
    @NotNull
    private ProcessingOrderItemStatus status;
}
