package com.arisee.restaurant.repository;


import com.arisee.restaurant.domain.processingOrder.TableProcessingOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface TableProcessingOrderRepository extends CrudRepository<TableProcessingOrder, BigInteger> {
    Optional<TableProcessingOrder> getByTableId(BigInteger tableId);
}
