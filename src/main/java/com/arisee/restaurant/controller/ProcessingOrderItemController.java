package com.arisee.restaurant.controller;

import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItem;
import com.arisee.restaurant.exception.NotFoundException;
import com.arisee.restaurant.model.processingOrder.ProcessingOrderItemForm;
import com.arisee.restaurant.service.ProcessingOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by NGOCHUY on 5/18/2017.
 */
@RestController
@RequestMapping("/api/processingOrdersItem")
public class ProcessingOrderItemController {
    @Autowired
    private ProcessingOrderItemService processingOrderItemService;

    @RequestMapping(method = RequestMethod.GET)
    public List<com.arisee.restaurant.model.processingOrder.ProcessingOrderItem> getAll() {
        return this.processingOrderItemService.getAll();
    }

    @RequestMapping(value = "/{tableId}/id/{id}/status/{status}",method = RequestMethod.POST)
    public ProcessingOrderItem setStatusItem(@PathVariable BigInteger tableId, @PathVariable Integer id,
                                             @PathVariable Integer status){
        return this.processingOrderItemService.setStatusItem(tableId,id,status)
                .orElseThrow(NotFoundException::new);
    }

//    @RequestMapping(value = "/{id}/tableId/{tableId}",method = RequestMethod.GET)
//    public ProcessingOrderItem FindById(@PathVariable Integer id, @PathVariable BigInteger tableId){
//        return this.processingOrderItemService.findById(id,tableId)
//                .orElseThrow(NotFoundException::new);
//    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ProcessingOrderItem insert(@Valid @RequestBody ProcessingOrderItemForm processingOrderItemForm){
//        return this.processingOrderItemService.create(processingOrderItemForm);
//    }
}
