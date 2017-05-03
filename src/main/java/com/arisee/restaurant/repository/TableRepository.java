package com.arisee.restaurant.repository;


import com.arisee.restaurant.domain.table.Table;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface TableRepository extends CrudRepository<Table, BigInteger> {
    Optional<Table> getById(BigInteger id);
}
