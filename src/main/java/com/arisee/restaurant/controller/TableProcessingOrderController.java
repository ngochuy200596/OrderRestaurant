package com.arisee.restaurant.controller;

import com.arisee.restaurant.domain.processingOrder.TableProcessingOrder;
import com.arisee.restaurant.exception.NotFoundException;
import com.arisee.restaurant.model.FormResponse;
import com.arisee.restaurant.model.processingOrder.TableProcessingOrderForm;
import com.arisee.restaurant.service.TableProcessingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/processingOrders")
public class TableProcessingOrderController {
    @Autowired
    private TableProcessingOrderService tableProcessingOrderService;

    @RequestMapping(method = RequestMethod.GET)
    public List<com.arisee.restaurant.model.processingOrder.TableProcessingOrder> getAll() {
        return this.tableProcessingOrderService.getAll();
    }

    @RequestMapping(value = "/{tableId}", method = RequestMethod.GET)
    public TableProcessingOrder getById(@PathVariable("tableId") BigInteger tableId) {
        return this.tableProcessingOrderService.getById(tableId)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/{tableId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("tableId") BigInteger tableId) {
        this.tableProcessingOrderService.delete(tableId);
    }

    @RequestMapping(value = "/{tableId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TableProcessingOrder update(@PathVariable BigInteger tableId, @Valid @RequestBody TableProcessingOrderForm tableProcessingOrderForm) {
        return this.tableProcessingOrderService.update(tableId, tableProcessingOrderForm)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public FormResponse insert(@Valid @RequestBody TableProcessingOrderForm tableProcessingOrderForm) {
        return FormResponse.builder()
                .id(this.tableProcessingOrderService.create(tableProcessingOrderForm).getTableId()).build();
    }
}
