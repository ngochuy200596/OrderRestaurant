package com.arisee.restaurant.repository;


import com.arisee.restaurant.domain.order.Order;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, BigInteger> {
    Optional<Order> getById(BigInteger id);
}
