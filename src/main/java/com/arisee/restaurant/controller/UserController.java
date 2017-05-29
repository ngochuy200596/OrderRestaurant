package com.arisee.restaurant.controller;


import com.arisee.restaurant.domain.user.User;
import com.arisee.restaurant.exception.NotFoundException;
import com.arisee.restaurant.model.user.UserForm;
import com.arisee.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<com.arisee.restaurant.model.user.User> getAll() {
        return this.userService.getAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getById(@PathVariable("id") BigInteger id) {
        return this.userService.getById(id)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id) {
        this.userService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User update(@PathVariable BigInteger id, @Valid @RequestBody UserForm userForm) {
        return this.userService.update(id, userForm).orElseThrow(NotFoundException::new);
    }

//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public User insert(@Valid @RequestBody UserForm userForm) {
//        return userService.create(userForm);
//    }

    @RequestMapping(method = RequestMethod.POST)
    public User login(@Valid @RequestBody UserForm userForm) {
        return this.userService.login(userForm.getUserName(),userForm.getPassWord())
                .orElseThrow(NotFoundException::new);
    }
}
