package com.arisee.restaurant.domain.category;


import com.arisee.restaurant.domain.Dish.Dish;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String name;

//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Dish> dish;

    public com.arisee.restaurant.model.category.Category toCategory(){
        com.arisee.restaurant.model.category.Category rs = new com.arisee.restaurant.model.category.Category();
        rs.setId(id);
        rs.setName(name);

        return rs;
    }
}
