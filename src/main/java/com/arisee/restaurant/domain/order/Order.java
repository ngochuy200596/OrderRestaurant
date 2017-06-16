package com.arisee.restaurant.domain.order;

import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItem;
import com.arisee.restaurant.model.order.OrderItemForm;
import com.arisee.restaurant.model.processingOrder.ProcessingOrderItemForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "`order`")
@Embeddable
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String customerName;
    private String phone;
    private LocalDateTime createdDate;
    private BigInteger tableId;
    private BigInteger userId;
    private BigDecimal total;

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
        rs.setCreatedDate(createdDate);
        rs.setTableId(tableId);
        rs.setUserId(userId);
        rs.setItems(getItems().stream().map(OrderItem::toForm).collect(Collectors.toList()));
        rs.setTotal(total);
        return rs;
    }


    public void setListOrderItems(List<OrderItemForm> listOrderItems) {
        items.clear();
        if (!CollectionUtils.isEmpty(listOrderItems)) {
            for (int i = 0; i < listOrderItems.size(); i++) {
                OrderItemForm form = listOrderItems.get(i);
                OrderItem item = new OrderItem();
                item.getPk().setId(i + 1);
                item.getPk().setOrder(this);
                item.setDescription(form.getDescription());
                item.setQuantity(form.getQuantity());
//                item.setDishId(form.getDishId());
                item.setDish(form.getDish());
                items.add(item);
            }
        }
    }

    public void setListOrderItems2(List<ProcessingOrderItem> listOrderItems) {
        items.clear();
        if (!CollectionUtils.isEmpty(listOrderItems)) {
            for (int i = 0; i < listOrderItems.size(); i++) {
                ProcessingOrderItem form = listOrderItems.get(i);
                OrderItem item = new OrderItem();
                item.getPk().setId(i + 1);
                item.getPk().setOrder(this);
                item.setDescription(form.getDescription());
                item.setQuantity(form.getQuantity());
                item.setDish(form.getDish());
                items.add(item);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        return getId().equals(order.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
