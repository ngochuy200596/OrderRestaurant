package com.arisee.restaurant.test;


import com.arisee.restaurant.Application;
import com.arisee.restaurant.domain.table.Table;
import com.arisee.restaurant.domain.user.User;
import com.arisee.restaurant.model.user.UserForm;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreate() {
        UserForm userForm = new UserForm();
        userForm.setFullName("Duy");
        userForm.setPassWord("1234");
        userForm.setUserName("duy@gmail.com");
        HttpEntity<UserForm> entity = new HttpEntity<>(userForm);
        User user =  this.restTemplate
                .exchange("/users", HttpMethod.POST, entity, User.class).getBody();
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isNotNull();
        Assertions.assertThat(user.getFullName()).isEqualTo(userForm.getFullName());
        Assertions.assertThat(user.getUserName()).isEqualTo(userForm.getUserName());
        Assertions.assertThat(user.getPassWord()).isEqualTo(userForm.getPassWord());


        UserForm form = new UserForm();
        form.getFullName();
        form.getUserName();
        form.getPassWord();
        HttpEntity<UserForm> reEntity = new HttpEntity<>(form);
        User users = this.restTemplate.exchange("/users/1", HttpMethod.GET, reEntity, User.class).getBody();
        Assertions.assertThat(users).isNotNull();
        Assertions.assertThat(users.getId()).isNotNull();
        Assertions.assertThat(users.getFullName()).isEqualTo("1");
        Assertions.assertThat(users.getUserName()).isEqualTo("1");
        Assertions.assertThat(users.getPassWord()).isEqualTo("1");
    }

    @Test
    public void testGetAll(){
        ParameterizedTypeReference<List<User>> responseType
                = new ParameterizedTypeReference<List<User>>() {
        };
        List<User> users = this.restTemplate
                .exchange("/users", HttpMethod.GET, null, responseType).getBody();

        Assertions.assertThat(users).hasSize(6);
    }

    @Test
    public void testDelete(){
        UserForm userForm = new UserForm();
        HttpEntity<UserForm> reEntity = new HttpEntity<>(userForm);
        this.restTemplate.exchange("/users/6", HttpMethod.DELETE, reEntity, User.class).getBody();
    }

    @Test
    public void testUpdate(){
        UserForm userForm = new UserForm();
        userForm.setFullName("Duy");
        userForm.setPassWord("1234");
        userForm.setUserName("duy@gmail.com");
        HttpEntity<UserForm> entity = new HttpEntity<>(userForm);
        User user =  this.restTemplate
                .exchange("/users/1", HttpMethod.POST, entity, User.class).getBody();
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isNotNull();
        Assertions.assertThat(user.getFullName()).isEqualTo(userForm.getFullName());
        Assertions.assertThat(user.getUserName()).isEqualTo(userForm.getUserName());
        Assertions.assertThat(user.getPassWord()).isEqualTo(userForm.getPassWord());

    }

}