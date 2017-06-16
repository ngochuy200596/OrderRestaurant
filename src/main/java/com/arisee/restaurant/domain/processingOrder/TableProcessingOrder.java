package com.arisee.restaurant.domain.processingOrder;


import com.arisee.restaurant.model.processingOrder.TableProcessingOrderForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@javax.persistence.Table(name = "table_processing_order")
public class TableProcessingOrder {
    @Id
    private BigInteger tableId;
    private String customerName;

    @Enumerated(EnumType.ORDINAL)
    private ProcessingOrderStatus status;
    private LocalDateTime createdDate;
    private String phone;

    @OneToMany(mappedBy = "pk.tableProcessingOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ProcessingOrderItem> items = new ArrayList<>();

    public void setListOrderItems(List<ProcessingOrderItem> items) {
        this.items.clear();
        int index = 1;
        for (ProcessingOrderItem item : items) {
            item.getPk().setId(index++);
            item.getPk().setTableProcessingOrder(this);
            this.items.add(item);
        }
    }

    public com.arisee.restaurant.model.processingOrder.TableProcessingOrder toTableProcessingOrder() {
        com.arisee.restaurant.model.processingOrder.TableProcessingOrder rs = new com.arisee.restaurant.model.processingOrder.TableProcessingOrder();
        rs.setTableId(tableId);
        rs.setCustomerName(customerName);
        rs.setPhone(phone);
        rs.setStatus(status);
        rs.setCreatedDate(createdDate);
        rs.setItems(getItems().stream().map(ProcessingOrderItem::toProcessingForm).collect(Collectors.toList()));
        return rs;
    }

    public TableProcessingOrderForm tableProcessingOrderForm() {
        TableProcessingOrderForm rs = new TableProcessingOrderForm();
        rs.setTableId(tableId);
        rs.setCustomerName(customerName);
        rs.setPhone(phone);
        rs.setStatus(status);
        rs.setCreatedDate(createdDate);
        rs.setItems(getItems().stream().map(ProcessingOrderItem::toProcessingForm).collect(Collectors.toList()));
        return rs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableProcessingOrder)) return false;

        TableProcessingOrder that = (TableProcessingOrder) o;

        return tableId.equals(that.tableId);
    }

    @Override
    public int hashCode() {
        return tableId.hashCode();
    }
}
