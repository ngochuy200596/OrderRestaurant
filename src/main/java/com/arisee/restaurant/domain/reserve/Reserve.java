package com.arisee.restaurant.domain.reserve;



import com.arisee.restaurant.jackson.LocalDateTimeJsonDeserializer;
import com.arisee.restaurant.jackson.LocalDateTimeJsonSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@javax.persistence.Table(name = "reserve")
@Data
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String customerName;
    private String phone;
    private Integer quantity;
    @JsonSerialize(using = LocalDateTimeJsonSerializer.class)
    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    private LocalDateTime scheduleOn;
    private LocalDateTime createdDate;
//    private BigInteger tableId;

    @ManyToOne
    @JoinColumn(name="tableId")
//    @JsonIgnore
    private com.arisee.restaurant.domain.table.Table table;

    public com.arisee.restaurant.model.reserve.Reserve toReserve(){
        com.arisee.restaurant.model.reserve.Reserve rs = new com.arisee.restaurant.model.reserve.Reserve();
        rs.setId(id);
        rs.setCustomerName(customerName);
        rs.setPhone(phone);
        rs.setQuantity(quantity);
//        rs.setTableId(tableId);
        rs.setTableId(table.getId());
        rs.setScheduleOn(scheduleOn);
        rs.setCreatedDate(createdDate);
        return rs;
    }

    public Reserve toReserveForm(){
        Reserve rs = new Reserve();
        rs.setId(id);
        rs.setCustomerName(customerName);
        rs.setPhone(phone);
        rs.setQuantity(quantity);
        rs.setTable(table);
        rs.setScheduleOn(scheduleOn);
        rs.setCreatedDate(createdDate);
        return rs;
    }

}
