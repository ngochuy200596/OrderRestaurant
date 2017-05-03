package com.arisee.restaurant.model.table;


import lombok.Data;

import java.math.BigInteger;
@Data
public class Table {
    private BigInteger id;
    private String name;
    private String location;

}
