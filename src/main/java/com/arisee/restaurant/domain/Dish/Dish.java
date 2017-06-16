package com.arisee.restaurant.domain.Dish;

import com.arisee.restaurant.domain.category.Category;
import com.arisee.restaurant.domain.order.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "dish")
@Data
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private DishStatus status;
    private BigDecimal price;
    private BigInteger categoryId;
    private String image;
    private BigInteger rank;

//    @ManyToOne
//    @JoinColumn(name = "categoryId")
//    @JsonIgnore
//    private Category category;

    public com.arisee.restaurant.model.dish.Dish toDish(){
        com.arisee.restaurant.model.dish.Dish rs = new com.arisee.restaurant.model.dish.Dish();
        rs.setId(id);
        rs.setName(name);
        rs.setStatus(status);
        rs.setPrice(price);
        rs.setCategoryId(categoryId);
        rs.setImage(image);
        rs.setRank(rank);
        return rs;
    }



}
