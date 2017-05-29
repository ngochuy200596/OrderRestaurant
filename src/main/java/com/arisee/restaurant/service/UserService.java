package com.arisee.restaurant.service;

import com.arisee.restaurant.domain.user.User;
import com.arisee.restaurant.model.user.UserForm;
import com.arisee.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<com.arisee.restaurant.model.user.User> getAll() {
        final List<com.arisee.restaurant.model.user.User> rs = new ArrayList<>();
        this.userRepository.findAll().forEach(user -> {
            rs.add(user.toUser());
        });
        return rs;
    }

    public User create(UserForm userForm) {
        User user = new User();
        user.setUserName(userForm.getUserName());
        user.setPassWord(userForm.getPassWord());
        return this.userRepository.save(user);
    }

    public Optional<User> getById(BigInteger id) {
        return this.userRepository.getById(id);
    }

    public void delete(BigInteger id) {
        getById(id).ifPresent(userRepository::delete);
    }

    public Optional<User> update(BigInteger id, UserForm userForm) {
        return getById(id).map(user -> {
            user.setUserName(userForm.getUserName());
            user.setPassWord(userForm.getPassWord());
            return this.userRepository.save(user);
        });
    }

    public Optional<User> login(String userName, String passWord) {
        return this.userRepository.login(userName,passWord);
    }
}
