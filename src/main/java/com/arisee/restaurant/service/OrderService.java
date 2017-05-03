package com.arisee.restaurant.service;

import com.arisee.restaurant.domain.order.Order;
import com.arisee.restaurant.model.order.OrderForm;
import com.arisee.restaurant.model.order.OrderItemForm;
import com.arisee.restaurant.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<com.arisee.restaurant.model.order.Order> getAll(){
        final List<com.arisee.restaurant.model.order.Order> rs = new ArrayList<>();
        this.orderRepository.findAll().forEach(order -> {
            rs.add(order.toOrder());
        });
        return rs;
    }
    public Optional<Order> getById(BigInteger id){
        return this.orderRepository.getById(id);
    }
    public void delete(BigInteger id){
        getById(id).ifPresent(orderRepository::delete);
    }

    public Optional<Order> update(BigInteger id, OrderForm orderForm){
        return getById(id).map(order -> {

           order.setCustomerName(orderForm.getCustomerName());
           order.setPhone(orderForm.getPhone());
           order.setStatus(orderForm.getStatus());
           order.setScheduleOn(orderForm.getScheduleOn());
           order.setListOrderItems(orderForm.getItems());
           return this.orderRepository.save(order);

        });
    }
    public Order create(OrderForm orderForm){
        Order order =new Order();
        order.setCustomerName(orderForm.getCustomerName());
        order.setPhone(orderForm.getPhone());
        order.setStatus(orderForm.getStatus());
        order.setScheduleOn(orderForm.getScheduleOn());
        order.setCreatedDate(LocalDateTime.now());
        order.setListOrderItems(orderForm.getItems());

        return this.orderRepository.save(order);
    }
}
