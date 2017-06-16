package com.arisee.restaurant.controller;

import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItem;
import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItemPK;
import com.arisee.restaurant.domain.processingOrder.TableProcessingOrder;
import com.arisee.restaurant.exception.NotFoundException;
import com.arisee.restaurant.model.processingOrder.ProcessingOrderItemForm;
import com.arisee.restaurant.service.ProcessingOrderItemService;
import com.arisee.restaurant.service.TableProcessingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.NotActiveException;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by NGOCHUY on 5/18/2017.
 */
@RestController
@RequestMapping("/api/processingOrdersItem")
public class ProcessingOrderItemController {
    private final ProcessingOrderItemService processingOrderItemService;
    private final TableProcessingOrderService tableProcessingOrderService;

    @Autowired
    public ProcessingOrderItemController(ProcessingOrderItemService processingOrderItemService, TableProcessingOrderService tableProcessingOrderService) {
        this.processingOrderItemService = processingOrderItemService;
        this.tableProcessingOrderService = tableProcessingOrderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<com.arisee.restaurant.model.processingOrder.ProcessingOrderItem> getAll() {
        return this.processingOrderItemService.getAll();
    }

    @RequestMapping(value = "/{tableId}/id/{id}/status/{status}", method = RequestMethod.POST)
    public ProcessingOrderItem setStatusItem(@PathVariable BigInteger tableId, @PathVariable Integer id,
                                             @PathVariable Integer status) {
        return this.processingOrderItemService.setStatusItem(tableId, id, status)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/{tableId}/id/{id}", method = RequestMethod.GET)
    public ProcessingOrderItem findOne(@PathVariable BigInteger tableId, @PathVariable Integer id) {
        ProcessingOrderItemPK processingOrderItemPK = new ProcessingOrderItemPK();
        processingOrderItemPK.setId(id);
        processingOrderItemPK.setTableProcessingOrder(tableProcessingOrderService.getById(tableId).orElseThrow(NotFoundException::new));
        return this.processingOrderItemService.findOne(processingOrderItemPK)
                .orElseThrow(NotFoundException::new);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{tableId}/id/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable BigInteger tableId, @PathVariable Integer id){
        ProcessingOrderItemPK processingOrderItemPK = new ProcessingOrderItemPK();
        processingOrderItemPK.setId(id);
        processingOrderItemPK.setTableProcessingOrder(tableProcessingOrderService.getById(tableId).orElseThrow(NotFoundException::new));
        this.processingOrderItemService.delete(processingOrderItemPK);
    }
}
