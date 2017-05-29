package com.arisee.restaurant.model.table;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class TableForm {
    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotBlank
    private Integer status;
}
