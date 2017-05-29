package com.arisee.restaurant.model.table;


import com.arisee.restaurant.domain.table.TableStatus;
import lombok.Data;

import java.math.BigInteger;
@Data
public class Table {
    private BigInteger id;
    private String name;
    private String location;
    private TableStatus status;
}
