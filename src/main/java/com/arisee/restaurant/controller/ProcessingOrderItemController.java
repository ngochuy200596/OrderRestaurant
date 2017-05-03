//package com.arisee.restaurant.controller;
//
//import com.arisee.restaurant.exception.NotFoundException;
//import com.arisee.restaurant.model.processingOrder.ProcessingOrderItem;
//import com.arisee.restaurant.model.processingOrder.ProcessingOrderItemForm;
//import com.arisee.restaurant.service.ProcessingOrderItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/processingOderItems")
//public class ProcessingOrderItemController {
//    @Autowired
//    private ProcessingOrderItemService processingOrderItemService;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public List<ProcessingOrderItem> getALl(){
//        return this.processingOrderItemService.getAll();
//    }
//
//    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
//    public com.arisee.restaurant.domain.processingOrder.ProcessingOrderItem getById(@PathVariable("id") Integer id){
//        return this.processingOrderItemService.getById(id).orElseThrow(NotFoundException::new);
//    }
//
//    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
//    public void delete(@PathVariable("id") Integer id){
//        this.processingOrderItemService.delete(id);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public com.arisee.restaurant.domain.processingOrder.ProcessingOrderItem update(@PathVariable Integer id, @Valid @RequestBody ProcessingOrderItemForm processingOrderItemForm) {
//        return this.processingOrderItemService.update(id, processingOrderItemForm).orElseThrow(NotFoundException::new);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public com.arisee.restaurant.domain.processingOrder.ProcessingOrderItem insert(@Valid @RequestBody ProcessingOrderItemForm processingOrderItemForm) {
//        return processingOrderItemService.create(processingOrderItemForm);
//    }
//}
