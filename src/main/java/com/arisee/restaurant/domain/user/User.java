package com.arisee.restaurant.domain.user;

import lombok.Data;


import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Data
@Table(name ="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    private String fullName;

    private String userName;

    private String passWord;



    public com.arisee.restaurant.model.user.User toUser(){
        com.arisee.restaurant.model.user.User rs = new com.arisee.restaurant.model.user.User();
        rs.setId(id);
        rs.setFullName(fullName);
        rs.setUserName(userName);
        rs.setPassWord(passWord);
        return rs;
    }
}
