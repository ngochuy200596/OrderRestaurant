package com.arisee.restaurant.repository;

import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItem;
import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItemPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface ProcessingOrderItemRepository extends CrudRepository<ProcessingOrderItem, ProcessingOrderItemPK> {
//    @Query("SELECT i FROM #{#entityName} i WHERE i.id = ?1 AND i.tableId = ?2")
//    Optional<ProcessingOrderItem> findById(Integer id, BigInteger tableId);
}
