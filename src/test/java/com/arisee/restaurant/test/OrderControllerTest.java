package com.arisee.restaurant.test;

import com.arisee.restaurant.Application;
import com.arisee.restaurant.domain.order.Order;
import com.arisee.restaurant.domain.order.OrderItem;
import com.arisee.restaurant.domain.order.OrderStatus;
import com.arisee.restaurant.model.order.OrderForm;
import com.arisee.restaurant.model.order.OrderItemForm;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class OrderControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testCreate() {
        OrderForm orderForm = new OrderForm();
        orderForm.setCustomerName("lam");
        orderForm.setPhone("0165");


        List<OrderItemForm> listOrderItems = new ArrayList<>();
        OrderItemForm form = new OrderItemForm();
        form.setDescription("vao");
//        form.setQuantity(1.00);
//        form.setDishId(BigInteger.valueOf(6));
        listOrderItems.add(form);


        OrderItemForm form1 = new OrderItemForm();
        form1.setDescription("hao");
//        form1.setQuantity(2);
//        form1.setDishId(BigInteger.valueOf(5));
        listOrderItems.add(form1);
        orderForm.setItems(listOrderItems);

        HttpEntity<OrderForm> entity = new HttpEntity<>(orderForm);
        Order order = this.restTemplate.exchange("/orders", HttpMethod.POST, entity, Order.class).getBody();
        Assertions.assertThat(order).isNotNull();
        Assertions.assertThat(order.getId()).isNotNull();
        Assertions.assertThat(order.getCustomerName()).isEqualTo(orderForm.getCustomerName());
        Assertions.assertThat(order.getPhone()).isEqualTo(orderForm.getPhone());



//        for (int i = 0; i < listOrderItems.size(); i++) {
//            OrderItemForm itemForm = listOrderItems.get(i);
//            OrderItem orderItem = order.getItems().get(i);
//            Assertions.assertThat(orderItem).isNotNull();
//            Assertions.assertThat(orderItem.getPk()).isNotNull();
//            Assertions.assertThat(orderItem.getDescription()).isEqualTo(itemForm.getDescription());
//            Assertions.assertThat(orderItem.getQuantity()).isEqualTo(itemForm.getQuantity());
//            Assertions.assertThat(orderItem.getDishId()).isEqualTo(itemForm.getDishId());
//        }
    }

    @Test
    public void testGetAll() {
        ParameterizedTypeReference<List<Order>> responseType= new ParameterizedTypeReference<List<Order>>() {

        };
        List<Order> orders = this.restTemplate
                .exchange("/orders",HttpMethod.GET,null,responseType).getBody();
        Assertions.assertThat(orders).hasSize(2);
    }
    @Test
    public void testDelete(){
        OrderForm orderForm = new OrderForm();
        HttpEntity<OrderForm> entity = new HttpEntity<>(orderForm);
        this.restTemplate.exchange("/orders/37",HttpMethod.DELETE,entity,Order.class).getBody();
    }

    @Test
    public void testUpdate(){
        OrderForm orderForm = new OrderForm();
        orderForm.setCustomerName("duy");
        orderForm.setPhone("132");


        List<OrderItemForm> listOrderItems = new ArrayList<>();
        OrderItemForm form = new OrderItemForm();
        form.setDescription("54654654");
//        form.setQuantity(7);
//        form.setDishId(BigInteger.valueOf(5));
        listOrderItems.add(form);

        orderForm.setItems(listOrderItems);
        HttpEntity<OrderForm> entity = new HttpEntity<>(orderForm);
        Order order = this.restTemplate.exchange("/orders/4", HttpMethod.POST, entity, Order.class).getBody();
        Assertions.assertThat(order).isNotNull();
        Assertions.assertThat(order.getId()).isNotNull();
        Assertions.assertThat(order.getCustomerName()).isEqualTo(orderForm.getCustomerName());
        Assertions.assertThat(order.getPhone()).isEqualTo(orderForm.getPhone());


    }
}
