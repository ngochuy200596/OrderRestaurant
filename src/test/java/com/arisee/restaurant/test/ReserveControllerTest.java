package com.arisee.restaurant.test;

import com.arisee.restaurant.Application;
import com.arisee.restaurant.domain.reserve.Reserve;
import com.arisee.restaurant.model.reserve.ReserveForm;
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

import javax.xml.ws.soap.SOAPBinding;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class ReserveControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreate(){
        ReserveForm reserveForm = new ReserveForm();
        reserveForm.setCustomerName("duy");
        reserveForm.setPhone("duy");
        reserveForm.setScheduleOn(LocalDateTime.of(2017,04,27,4,45));


        HttpEntity<ReserveForm> entity = new HttpEntity<>(reserveForm);

        Reserve reserve = this.restTemplate.exchange("/reserves",HttpMethod.POST,entity,Reserve.class).getBody();

        Assertions.assertThat(reserve).isNotNull();
        Assertions.assertThat(reserve.getId()).isNotNull();
        Assertions.assertThat(reserve.getCustomerName()).isEqualTo(reserveForm.getCustomerName());
        Assertions.assertThat(reserve.getPhone()).isEqualTo(reserveForm.getPhone());
        Assertions.assertThat(reserve.getScheduleOn()).isEqualTo(reserveForm.getScheduleOn());

        ReserveForm form = new ReserveForm();
        form.getCustomerName();
        form.getPhone();

        HttpEntity<ReserveForm> reEntity = new HttpEntity<>(reserveForm);
        Reserve reserves = this.restTemplate.exchange("/reserves/18",HttpMethod.GET,reEntity ,Reserve.class).getBody();

        Assertions.assertThat(reserves).isNotNull();
        Assertions.assertThat(reserves.getId()).isNotNull();
        Assertions.assertThat(reserves.getCustomerName()).isEqualTo("duy");
        Assertions.assertThat(reserves.getPhone()).isEqualTo("duy");
//        Assertions.assertThat(reserves.getScheduleOn()).isEqualTo(LocalDateTime.of(2017,04,03,16,58,42));

    }

    @Test
    public void testGetAll(){
        ParameterizedTypeReference<List<Reserve>> responseType = new ParameterizedTypeReference<List<Reserve>>(){};

        List<Reserve> reserves = this.restTemplate
                .exchange("/reserves", HttpMethod.GET,null,responseType).getBody();
        Assertions.assertThat(reserves).hasSize(5);
    }

    @Test
    public void testDelete(){
        ReserveForm reserveForm =  new ReserveForm();
        HttpEntity<ReserveForm> entity = new HttpEntity<>(reserveForm);
        this.restTemplate.exchange("/reserves/11", HttpMethod.DELETE, entity, Reserve.class).getBody();

    }

    @Test
    public void testUpdate(){
        ReserveForm reserveForm = new ReserveForm();
        reserveForm.setCustomerName("duy");
        reserveForm.setPhone("0165");
        reserveForm.setScheduleOn(LocalDateTime.now());

        HttpEntity<ReserveForm> entity = new HttpEntity<>(reserveForm);
        Reserve reserve = this.restTemplate.exchange("/reserves/1", HttpMethod.POST, entity, Reserve.class).getBody();

        Assertions.assertThat(reserve).isNotNull();
        Assertions.assertThat(reserve.getId()).isNotNull();
        Assertions.assertThat(reserve.getCustomerName()).isEqualTo(reserveForm.getCustomerName());
        Assertions.assertThat(reserve.getPhone()).isEqualTo(reserveForm.getPhone());

    }

}
