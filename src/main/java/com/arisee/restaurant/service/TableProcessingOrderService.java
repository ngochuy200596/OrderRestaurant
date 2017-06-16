package com.arisee.restaurant.service;

import com.arisee.restaurant.domain.Dish.Dish;
import com.arisee.restaurant.domain.order.Order;
import com.arisee.restaurant.domain.order.OrderItem;
import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItem;
import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItemStatus;
import com.arisee.restaurant.domain.processingOrder.ProcessingOrderStatus;
import com.arisee.restaurant.domain.processingOrder.TableProcessingOrder;

import com.arisee.restaurant.model.processingOrder.ProcessingOrderItemForm;
import com.arisee.restaurant.model.processingOrder.TableProcessingOrderForm;
import com.arisee.restaurant.repository.OrderRepository;
import com.arisee.restaurant.repository.TableProcessingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableProcessingOrderService {
    private final TableProcessingOrderRepository tableProcessingOrderRepository;
    private final DishService dishService;
    private final OrderRepository orderRepository;
    private final TableService tableService;


    @Autowired
    public TableProcessingOrderService(TableProcessingOrderRepository tableProcessingOrderRepository, DishService dishService,
                                       OrderRepository orderRepository, TableService tableService) {
        this.tableProcessingOrderRepository = tableProcessingOrderRepository;
        this.dishService = dishService;
        this.orderRepository = orderRepository;
        this.tableService = tableService;
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
        tableProcessingOrder.setPhone(tableProcessingOrderForm.getPhone());
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
                item.setStatus(ProcessingOrderItemStatus.AVAILABLE);
                item.setDish(dishService.getById(itemForm.getDish().getId()).orElse(null));
                tableProcessingOrder.getItems().add(item);
                i++;
                this.dishService.increseRank(itemForm.getDish().getId());
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
            tableProcessingOrder.setPhone(tableProcessingOrderForm.getPhone());

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

    public BigDecimal totalAmountPayable(BigInteger tableId) {
        return this.getById(tableId).map(tableProcessingOrder -> tableProcessingOrder.getItems()
                .stream()
                .map(ProcessingOrderItem::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add))
                .orElse(BigDecimal.ZERO);
    }

    @Transactional
    public Optional<Order> pay(BigInteger tableId, BigInteger userId) {
        return this.getById(tableId).map(tableProcessingOrder -> {
            Order order = new Order();
            order.setTotal(this.totalAmountPayable(tableId));
            order.setPhone(tableProcessingOrder.getPhone());
            order.setCreatedDate(LocalDateTime.now());
            order.setCustomerName(tableProcessingOrder.getCustomerName());
            order.setTableId(tableProcessingOrder.getTableId());
            order.setUserId(userId);
            order.setListOrderItems2(tableProcessingOrder.getItems());
            order = this.orderRepository.save(order);
            this.tableProcessingOrderRepository.delete(tableProcessingOrder);
            this.tableService.setStatus(tableId, 1);
            return order;
        });
    }

    public Optional<TableProcessingOrder> addOrderItem(BigInteger tableId, ProcessingOrderItemForm orderItemForm) {
        return this.getById(tableId).map(tableProcessingOrder -> {
            int i = 1;
            ProcessingOrderItem item = null;
            for (ProcessingOrderItem _item : tableProcessingOrder.getItems()) {
                if (_item.getDish().getId() == orderItemForm.getDish().getId() &&
                        _item.getDescription().equals(orderItemForm.getDescription()) &&
                        _item.getStatus().equals(orderItemForm.getStatus())) {
                    _item.setQuantity(_item.getQuantity().add(orderItemForm.getQuantity()));
                    item = _item;
                }
                tableProcessingOrder.setCreatedDate(LocalDateTime.now());
                i++;
            }
            if (item == null) {
                item = new ProcessingOrderItem();
                item.getPk().setId(i);
                item.getPk().setTableProcessingOrder(tableProcessingOrder);
                item.setStatus(ProcessingOrderItemStatus.AVAILABLE);
                item.setDish(dishService.getById(orderItemForm.getDish().getId()).orElse(null));
                item.setQuantity(orderItemForm.getQuantity());
                item.setDescription(orderItemForm.getDescription());
                tableProcessingOrder.getItems().add(item);
            }
            this.dishService.increseRank(orderItemForm.getDish().getId());
            return this.tableProcessingOrderRepository.save(tableProcessingOrder);
        });
    }
}
