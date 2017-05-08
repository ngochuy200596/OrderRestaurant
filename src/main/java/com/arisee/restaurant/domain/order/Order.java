package com.arisee.restaurant.domain.order;

import com.arisee.restaurant.model.order.OrderItemForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Table;

@Entity
@Table(name = "`order`")
@Data
@Embeddable
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String customerName;
    private String phone;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;
    private LocalDateTime scheduleOn;
    private LocalDateTime createdDate;
    private BigInteger tableId;
    private BigInteger userId;

    @OneToMany(mappedBy = "pk.order", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItem> items = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "tableId")
//    @JsonIgnore
//    private com.arisee.restaurant.domain.table.Table table;

    public com.arisee.restaurant.model.order.Order toOrder() {
        com.arisee.restaurant.model.order.Order rs = new com.arisee.restaurant.model.order.Order();
        rs.setId(id);
        rs.setCustomerName(customerName);
        rs.setPhone(phone);
        rs.setStatus(status);
        rs.setScheduleOn(scheduleOn);
        rs.setCreatedDate(createdDate);
        rs.setTableId(tableId);
        rs.setUserId(userId);
        rs.setItems(getItems().stream().map(OrderItem::toForm).collect(Collectors.toList()));

        return rs;
    }


    public void setListOrderItems(List<OrderItemForm> listOrderItems){
        items.clear();
        if(!CollectionUtils.isEmpty(listOrderItems)){
            for(int i = 0; i < listOrderItems.size();i++){
                OrderItemForm form = listOrderItems.get(i);
                OrderItem item = new OrderItem();
                item.getPk().setId(i + 1);
                item.getPk().setOrder(this);
                item.setDescription(form.getDescription());
                item.setQuantity(form.getQuantity());
                item.setDishId(form.getDishId());

                items.add(item);
            }
        }
    }
}
