package com.arisee.restaurant.controller;

import com.arisee.restaurant.domain.order.Order;
import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItem;
import com.arisee.restaurant.domain.processingOrder.TableProcessingOrder;
import com.arisee.restaurant.exception.NotFoundException;
import com.arisee.restaurant.model.FormResponse;
import com.arisee.restaurant.model.processingOrder.ProcessingOrderItemForm;
import com.arisee.restaurant.model.processingOrder.TableProcessingOrderForm;
import com.arisee.restaurant.service.TableProcessingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/processingOrders")
public class TableProcessingOrderController {
    @Autowired
    private TableProcessingOrderService tableProcessingOrderService;

    @RequestMapping(method = RequestMethod.GET)
    public List<com.arisee.restaurant.model.processingOrder.TableProcessingOrder> getAll() {
        return this.tableProcessingOrderService.getAll();
    }

    @RequestMapping(value = "/{tableId}", method = RequestMethod.GET)
    public com.arisee.restaurant.model.processingOrder.TableProcessingOrder getById(@PathVariable("tableId") BigInteger tableId) {
        return this.tableProcessingOrderService.getById(tableId).map(TableProcessingOrder::toTableProcessingOrder)
                .orElseThrow(NotFoundException::new);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{tableId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("tableId") BigInteger tableId) {
        this.tableProcessingOrderService.delete(tableId);
    }

    @RequestMapping(value = "/{tableId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public com.arisee.restaurant.model.processingOrder.TableProcessingOrder update(@PathVariable BigInteger tableId,
                                                                                   @Valid @RequestBody TableProcessingOrderForm tableProcessingOrderForm) {
        return this.tableProcessingOrderService.update(tableId, tableProcessingOrderForm).map(TableProcessingOrder::toTableProcessingOrder)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/{tableId}/status/{status}", method = RequestMethod.POST)
    public TableProcessingOrder setStatus(@PathVariable BigInteger tableId, @PathVariable Integer status) {
        return this.tableProcessingOrderService.setStatus(tableId, status)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/{tableId}/disTable/{disTableId}", method = RequestMethod.POST)
    public TableProcessingOrder move(@PathVariable BigInteger tableId, @PathVariable BigInteger disTableId) {
        return this.tableProcessingOrderService.moveOrderByTableId(tableId, disTableId)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public FormResponse insert(@Valid @RequestBody TableProcessingOrderForm tableProcessingOrderForm) {
        return FormResponse.builder()
                .id(this.tableProcessingOrderService.create(tableProcessingOrderForm).getTableId()).build();
    }

    @RequestMapping(value = "/{tableId}/customer/{customerName}", method = RequestMethod.POST)
    public TableProcessingOrder addStatus(@PathVariable BigInteger tableId, @PathVariable String customerName) {
        return this.tableProcessingOrderService.addCustomer(tableId, customerName)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/total/{tableId}", method = RequestMethod.GET)
    public BigDecimal totalAmountPayable(@PathVariable BigInteger tableId) {
        return this.tableProcessingOrderService.totalAmountPayable(tableId);
    }

    @RequestMapping(value = "/{tableId}/user/{userId}", method = RequestMethod.POST)
    public com.arisee.restaurant.model.order.Order pay(@PathVariable BigInteger tableId, @PathVariable BigInteger userId) {
        return this.tableProcessingOrderService.pay(tableId, userId).map(Order::toOrder)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/addItem/{tableId}", method = RequestMethod.POST)
    public TableProcessingOrder addOrderItem(@PathVariable BigInteger tableId,@Valid @RequestBody ProcessingOrderItemForm orderItemForm) {
        return this.tableProcessingOrderService.addOrderItem(tableId, orderItemForm)
                .orElseThrow(NotFoundException::new);
    }

}
