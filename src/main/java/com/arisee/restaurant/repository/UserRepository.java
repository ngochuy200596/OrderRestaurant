package com.arisee.restaurant.repository;

import com.arisee.restaurant.domain.user.User;

import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, BigInteger> {
    Optional<User> getById(BigInteger id);
}
