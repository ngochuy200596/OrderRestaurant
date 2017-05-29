package com.arisee.restaurant.repository;

import com.arisee.restaurant.domain.user.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, BigInteger> {
    Optional<User> getById(BigInteger id);

    @Query("SELECT u FROM #{#entityName} u WHERE u.userName = ?1 AND u.passWord =?2")
    Optional<User> login(String userName, String passWord);
}
