package com.arisee.restaurant.test;

import com.arisee.restaurant.Application;
import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItemStatus;
import com.arisee.restaurant.domain.processingOrder.ProcessingOrderStatus;
import com.arisee.restaurant.domain.processingOrder.TableProcessingOrder;
import com.arisee.restaurant.model.processingOrder.ProcessingOrderItemForm;
import com.arisee.restaurant.model.processingOrder.TableProcessingOrderForm;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = Application.class)
public class TableProcessingOrderControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreate(){
        TableProcessingOrderForm orderForm =  new TableProcessingOrderForm();
        orderForm.setTableId(BigInteger.valueOf(6));
        orderForm.setCustomerName("hao");
        orderForm.setPhone("123");
        orderForm.setStatus(ProcessingOrderStatus.AVAILABLE);
        orderForm.setCreatedDate(LocalDateTime.of(2017,04,27,4,41));
        orderForm.setScheduleOn(LocalDateTime.of(2017,04,27,4,41));

        List<ProcessingOrderItemForm> orderItemForms = new ArrayList<>();
        ProcessingOrderItemForm form = new ProcessingOrderItemForm();
        form.setDescription("con");
        form.setQuantity(1);
        form.setStatus(ProcessingOrderItemStatus.NOT_AVAILABLE);
        orderItemForms.add(form);
        orderForm.setItems(orderItemForms);

        HttpEntity<TableProcessingOrderForm> entity = new HttpEntity<>(orderForm);

        TableProcessingOrder processingOrder = this.restTemplate
                .exchange("/processingOrders", HttpMethod.POST,entity,TableProcessingOrder.class).getBody();
    }

    @Test
    public void testGetAll(){
        ParameterizedTypeReference<List<TableProcessingOrder>> responseType =
                new ParameterizedTypeReference<List<TableProcessingOrder>>() {
        };
        List<TableProcessingOrder> orderList = this.restTemplate
                .exchange("/processingOrders",HttpMethod.GET,null,responseType).getBody();
        Assertions.assertThat(orderList).hasSize(4);
    }

    @Test
    public void testDelete(){
        TableProcessingOrderForm orderForm = new TableProcessingOrderForm();
        HttpEntity<TableProcessingOrderForm> entity = new HttpEntity<>(orderForm);
        this.restTemplate.exchange("/processingOrders/1",HttpMethod.DELETE,entity,TableProcessingOrder.class);
    }

    @Test
    public void testUpdate(){
        TableProcessingOrderForm orderForm = new TableProcessingOrderForm();
        orderForm.setTableId(BigInteger.valueOf(4));
        orderForm.setCustomerName("hao");
        orderForm.setPhone("123");
        orderForm.setStatus(ProcessingOrderStatus.AVAILABLE);
        orderForm.setCreatedDate(LocalDateTime.now());
        orderForm.setScheduleOn(LocalDateTime.now());


        List<ProcessingOrderItemForm> orderItemForms = new ArrayList<>();
        ProcessingOrderItemForm form = new ProcessingOrderItemForm();
        form.setDescription("con");
        form.setQuantity(1);
        form.setStatus(ProcessingOrderItemStatus.NOT_AVAILABLE);
        orderItemForms.add(form);
        orderForm.setItems(orderItemForms);

        HttpEntity<TableProcessingOrderForm> entity = new HttpEntity<>(orderForm);

        TableProcessingOrder processingOrder = this.restTemplate
                .exchange("/processingOrders/4", HttpMethod.POST,entity,TableProcessingOrder.class).getBody();
    }
}
