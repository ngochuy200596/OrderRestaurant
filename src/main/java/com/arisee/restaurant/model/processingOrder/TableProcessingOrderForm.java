package com.arisee.restaurant.model.processingOrder;


import com.arisee.restaurant.domain.processingOrder.ProcessingOrderStatus;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TableProcessingOrderForm {
    @NotNull
    private BigInteger tableId;
//    @NotBlank
    @NotNull
    private String customerName;
    @NotNull
    private String phone;
    @NotNull
    private ProcessingOrderStatus status;
    private LocalDateTime createdDate;
    @NotEmpty
    private List<ProcessingOrderItemForm> items;
}
