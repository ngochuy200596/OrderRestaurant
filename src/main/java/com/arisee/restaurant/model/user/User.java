package com.arisee.restaurant.model.user;


import lombok.Data;

import java.math.BigInteger;
@Data
public class User {
    private BigInteger id;

    private String fullName;

    private String userName;

    private String passWord;



}
