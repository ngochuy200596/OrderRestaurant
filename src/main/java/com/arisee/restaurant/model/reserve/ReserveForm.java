package com.arisee.restaurant.model.reserve;


import com.arisee.restaurant.domain.table.Table;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class ReserveForm {
    @NotBlank
    private String customerName;
    @NotBlank
    private String phone;
    private LocalDateTime scheduleOn;
    @NotNull
//    private BigInteger tableId;
    private com.arisee.restaurant.model.table.Table table;
    @NotNull
    private Integer quantity;

}
