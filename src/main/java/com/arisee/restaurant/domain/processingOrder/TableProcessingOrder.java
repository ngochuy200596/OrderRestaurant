package com.arisee.restaurant.domain.processingOrder;


import com.arisee.restaurant.model.processingOrder.ProcessingOrderItemForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@javax.persistence.Table(name = "table_processing_order")
public class TableProcessingOrder {
    @Id
    private BigInteger tableId;
    private String customerName;
    private String phone;

    @Enumerated(EnumType.ORDINAL)
    private ProcessingOrderStatus status;
    private LocalDateTime scheduleOn;
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "pk.tableProcessingOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ProcessingOrderItem> items = new ArrayList<>();


    public com.arisee.restaurant.model.processingOrder.TableProcessingOrder toTableProcessingOrder() {
        com.arisee.restaurant.model.processingOrder.TableProcessingOrder rs = new com.arisee.restaurant.model.processingOrder.TableProcessingOrder();
        rs.setTableId(tableId);
        rs.setCustomerName(customerName);
        rs.setPhone(phone);
        rs.setStatus(status);
        rs.setScheduleOn(scheduleOn);
        rs.setCreatedDate(createdDate);
        rs.setItems(getItems().stream().map(ProcessingOrderItem::toProcessingForm).collect(Collectors.toList()));
        return rs;
    }

    public void setListProcessingOrderItem(List<ProcessingOrderItemForm> listProcessingOrderItem) {
        items.clear();
        if (!CollectionUtils.isEmpty(listProcessingOrderItem)) {
            for (int i = 0; i < listProcessingOrderItem.size(); i++) {
                ProcessingOrderItemForm form = listProcessingOrderItem.get(i);
                ProcessingOrderItem item = new ProcessingOrderItem();
                item.getPk().setId(i + 1);
                item.getPk().setTableProcessingOrder(this);
                item.setDescription(form.getDescription());
                item.setQuantity(form.getQuantity());
                item.setStatus(form.getStatus());
                items.add(item);
            }
        }

    }
}
