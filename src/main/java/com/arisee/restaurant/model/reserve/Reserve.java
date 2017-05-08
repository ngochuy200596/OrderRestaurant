package com.arisee.restaurant.model.reserve;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
@Data
public class Reserve {
    private BigInteger id;
    private String customerName;
    private String phone;
    private LocalDateTime scheduleOn;
    private LocalDateTime createdDate;
    private BigInteger tableId;
}
