package com.arisee.restaurant.service;

import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItem;
import com.arisee.restaurant.domain.processingOrder.ProcessingOrderStatus;
import com.arisee.restaurant.domain.processingOrder.TableProcessingOrder;

import com.arisee.restaurant.model.processingOrder.ProcessingOrderItemForm;
import com.arisee.restaurant.model.processingOrder.TableProcessingOrderForm;
import com.arisee.restaurant.repository.TableProcessingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableProcessingOrderService {
    private final TableProcessingOrderRepository tableProcessingOrderRepository;
    private final DishService dishService;

    @Autowired
    public TableProcessingOrderService(TableProcessingOrderRepository tableProcessingOrderRepository, DishService dishService) {
        this.tableProcessingOrderRepository = tableProcessingOrderRepository;
        this.dishService = dishService;
    }

    public List<com.arisee.restaurant.model.processingOrder.TableProcessingOrder> getAll() {
        final List<com.arisee.restaurant.model.processingOrder.TableProcessingOrder> rs = new ArrayList<>();
        this.tableProcessingOrderRepository.findAll().forEach(tableProcessingOrder -> {
            rs.add(tableProcessingOrder.toTableProcessingOrder());
        });
        return rs;
    }

    public Optional<TableProcessingOrder> getById(BigInteger tableId) {
        return this.tableProcessingOrderRepository.getByTableId(tableId);
    }

    public void delete(BigInteger tableId) {
        getById(tableId).ifPresent(tableProcessingOrderRepository::delete);
    }

    @Transactional
    public TableProcessingOrder create(TableProcessingOrderForm tableProcessingOrderForm) {
        TableProcessingOrder tableProcessingOrder = new TableProcessingOrder();
        tableProcessingOrder.setTableId(tableProcessingOrderForm.getTableId());
        tableProcessingOrder.setCustomerName(tableProcessingOrderForm.getCustomerName());
        tableProcessingOrder.setStatus(tableProcessingOrderForm.getStatus());
        tableProcessingOrder.setCreatedDate(LocalDateTime.now());
        tableProcessingOrder.getItems().clear();
        int i = 0;
        if (!CollectionUtils.isEmpty(tableProcessingOrderForm.getItems())) {
            for (ProcessingOrderItemForm itemForm : tableProcessingOrderForm.getItems()) {
                ProcessingOrderItem item = new ProcessingOrderItem();
                item.getPk().setId(i + 1);
                item.getPk().setTableProcessingOrder(tableProcessingOrder);
                item.setDescription(itemForm.getDescription());
                item.setQuantity(itemForm.getQuantity());
                item.setStatus(itemForm.getStatus());
                item.setDish(dishService.getById(itemForm.getDish().getId()).orElse(null));
                tableProcessingOrder.getItems().add(item);
            }
        }

        return this.tableProcessingOrderRepository.save(tableProcessingOrder);
    }

    @Transactional
    public Optional<TableProcessingOrder> update(BigInteger tableId, TableProcessingOrderForm tableProcessingOrderForm) {
        return getById(tableId).map(tableProcessingOrder -> {
            tableProcessingOrder.setCustomerName(tableProcessingOrderForm.getCustomerName());
            tableProcessingOrder.setStatus(tableProcessingOrderForm.getStatus());
            tableProcessingOrder.setCreatedDate(LocalDateTime.now());

            int i = 0;
            if (!CollectionUtils.isEmpty(tableProcessingOrderForm.getItems())) {
                tableProcessingOrder.setListOrderItems(tableProcessingOrderForm.getItems().stream().map(itemForm -> {
                    ProcessingOrderItem item = new ProcessingOrderItem();
                    item.setDescription(itemForm.getDescription());
                    item.setQuantity(itemForm.getQuantity());
                    item.setStatus(itemForm.getStatus());
                    item.setDish(dishService.getById(itemForm.getDish().getId()).orElse(null));
                    return item;
                }).collect(Collectors.toList()));
            }
            return this.tableProcessingOrderRepository.save(tableProcessingOrder);
        });

    }

    public Optional<TableProcessingOrder> setStatus(BigInteger tableId, Integer desStatus) {
        return getById(tableId).map(tableProcessingOrder -> {
            tableProcessingOrder.setStatus(ProcessingOrderStatus.values()[desStatus]);
            return this.tableProcessingOrderRepository.save(tableProcessingOrder);
        });
    }

    public Optional<TableProcessingOrder> moveOrderByTableId(BigInteger preTableId, BigInteger disTableId) {
        return this.getById(preTableId).map(tableProcessingOrder -> {
            TableProcessingOrder _tableProcessingOrder = new TableProcessingOrder();
            _tableProcessingOrder.setTableId(disTableId);
            _tableProcessingOrder.setCreatedDate(tableProcessingOrder.getCreatedDate());
            _tableProcessingOrder.setCustomerName(tableProcessingOrder.getCustomerName());
            _tableProcessingOrder.setStatus(tableProcessingOrder.getStatus());
            _tableProcessingOrder.setListOrderItems(tableProcessingOrder.getItems()
                    .stream()
                    .map(processingOrderItem -> {
                        ProcessingOrderItem item = new ProcessingOrderItem();
                        item.setDish(processingOrderItem.getDish());
                        item.setQuantity(processingOrderItem.getQuantity());
                        item.setDescription(processingOrderItem.getDescription());
                        item.setStatus(processingOrderItem.getStatus());
                        return item;
                    }).collect(Collectors.toList()));
            _tableProcessingOrder = this.tableProcessingOrderRepository.save(_tableProcessingOrder);
            this.tableProcessingOrderRepository.delete(tableProcessingOrder);
            return _tableProcessingOrder;
        });
    }

    public Optional<TableProcessingOrder> addCustomer(BigInteger tableId, String customerName) {
        return this.getById(tableId).map(tableProcessingOrder -> {
            tableProcessingOrder.setCustomerName(customerName);
            return this.tableProcessingOrderRepository.save(tableProcessingOrder);
        });
    }
}
