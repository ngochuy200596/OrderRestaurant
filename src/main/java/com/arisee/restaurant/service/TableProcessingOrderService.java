package com.arisee.restaurant.service;

import com.arisee.restaurant.domain.processingOrder.TableProcessingOrder;

import com.arisee.restaurant.model.processingOrder.TableProcessingOrderForm;
import com.arisee.restaurant.repository.TableProcessingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TableProcessingOrderService {
    private final TableProcessingOrderRepository tableProcessingOrderRepository;
    @Autowired
    public TableProcessingOrderService(TableProcessingOrderRepository tableProcessingOrderRepository) {
        this.tableProcessingOrderRepository = tableProcessingOrderRepository;
    }

    public List<com.arisee.restaurant.model.processingOrder.TableProcessingOrder> getAll(){
        final List<com.arisee.restaurant.model.processingOrder.TableProcessingOrder> rs = new ArrayList<>();
        this.tableProcessingOrderRepository.findAll().forEach(tableProcessingOrder -> {
            rs.add(tableProcessingOrder.toTableProcessingOrder());
        });
        return rs;
    }

    public Optional<TableProcessingOrder> getById(BigInteger tableId){
        return this.tableProcessingOrderRepository.getByTableId(tableId);
    }

    public void delete(BigInteger tableId){
        getById(tableId).ifPresent(tableProcessingOrderRepository::delete);
    }


    public TableProcessingOrder create(TableProcessingOrderForm tableProcessingOrderForm){
        TableProcessingOrder tableProcessingOrder = new TableProcessingOrder();
        tableProcessingOrder.setTableId(tableProcessingOrderForm.getTableId());
        tableProcessingOrder.setCustomerName(tableProcessingOrderForm.getCustomerName());
        tableProcessingOrder.setPhone(tableProcessingOrderForm.getPhone());
        tableProcessingOrder.setStatus(tableProcessingOrderForm.getStatus());
        tableProcessingOrder.setScheduleOn(tableProcessingOrderForm.getScheduleOn());
        tableProcessingOrder.setCreatedDate(LocalDateTime.now());
        tableProcessingOrder.setListProcessingOrderItem(tableProcessingOrderForm.getItems());
        return this.tableProcessingOrderRepository.save(tableProcessingOrder);
    }

    public Optional<TableProcessingOrder> update(BigInteger tableId, TableProcessingOrderForm tableProcessingOrderForm){
        return getById(tableId).map(tableProcessingOrder -> {
            tableProcessingOrder.setCustomerName(tableProcessingOrderForm.getCustomerName());
            tableProcessingOrder.setPhone(tableProcessingOrder.getPhone());
            tableProcessingOrder.setStatus(tableProcessingOrderForm.getStatus());
            tableProcessingOrder.setScheduleOn(tableProcessingOrderForm.getScheduleOn());
            tableProcessingOrder.setListProcessingOrderItem(tableProcessingOrderForm.getItems());
            return this.tableProcessingOrderRepository.save(tableProcessingOrder);
        });

    }
}
