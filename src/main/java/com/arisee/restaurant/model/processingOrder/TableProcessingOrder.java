package com.arisee.restaurant.model.processingOrder;

import com.arisee.restaurant.domain.processingOrder.ProcessingOrderStatus;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TableProcessingOrder {
    private BigInteger tableId;
    private String customerName;
    private String phone;
    private ProcessingOrderStatus status;
    private LocalDateTime createdDate;
    private List<ProcessingOrderItemForm> items;
}
