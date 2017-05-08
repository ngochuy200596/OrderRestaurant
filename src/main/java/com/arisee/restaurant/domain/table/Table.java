package com.arisee.restaurant.domain.table;

import com.arisee.restaurant.domain.order.Order;
import com.arisee.restaurant.domain.processingOrder.TableProcessingOrder;
import com.arisee.restaurant.domain.reserve.Reserve;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@javax.persistence.Table(name = "`table`")
@Data
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String name;
    private String location;

//    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Reserve> reserve;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private TableProcessingOrder tableProcessingOrder;

    public com.arisee.restaurant.model.table.Table toTable(){
        com.arisee.restaurant.model.table.Table rs = new com.arisee.restaurant.model.table.Table();
        rs.setId(id);
        rs.setName(name);
        rs.setLocation(location);
        return rs;
    }
//    @OneToMany(mappedBy = "table",cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Order> orders;

}
