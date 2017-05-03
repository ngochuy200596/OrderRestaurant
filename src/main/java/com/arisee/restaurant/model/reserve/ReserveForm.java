package com.arisee.restaurant.model.reserve;


import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigInteger;
import java.time.LocalDateTime;
@Data
public class ReserveForm {
    @NotBlank
    private String customerName;
    @NotBlank
    private String phone;

    private LocalDateTime scheduleOn;

}
