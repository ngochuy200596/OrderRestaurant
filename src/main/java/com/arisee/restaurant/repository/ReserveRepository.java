package com.arisee.restaurant.repository;


import com.arisee.restaurant.domain.reserve.Reserve;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ReserveRepository extends CrudRepository<Reserve, BigInteger> {
    Optional<Reserve> getById(BigInteger id);

    @Query("SELECT r FROM #{#entityName} r WHERE r.table.id = ?1")
    Optional<List<Reserve>> findByTableId(BigInteger tableId);
}
