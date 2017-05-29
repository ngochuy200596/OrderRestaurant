package com.arisee.restaurant.controller;


import com.arisee.restaurant.domain.order.Order;
import com.arisee.restaurant.exception.NotFoundException;
import com.arisee.restaurant.model.order.OrderForm;
import com.arisee.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public List<com.arisee.restaurant.model.order.Order> getAll(){
        return this.orderService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order getById(@PathVariable("id") BigInteger id){
        return orderService.getById(id).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id){
        this.orderService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Order update(@PathVariable BigInteger id, @Valid @RequestBody OrderForm orderForm) {
        return orderService.update(id, orderForm).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Order insert(@Valid  @RequestBody OrderForm orderForm) {
        return orderService.create(orderForm);
    }
}

