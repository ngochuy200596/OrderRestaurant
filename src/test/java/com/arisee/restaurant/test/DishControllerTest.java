package com.arisee.restaurant.test;

import com.arisee.restaurant.Application;
import com.arisee.restaurant.domain.Dish.Dish;
import com.arisee.restaurant.domain.Dish.DishStatus;
import com.arisee.restaurant.model.dish.DishForm;
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
import java.util.List;

import static com.arisee.restaurant.domain.Dish.DishStatus.AVAILABLE;
import static com.arisee.restaurant.domain.Dish.DishStatus.NOT_AVAILABLE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class DishControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreate(){
        DishForm dishForm = new DishForm();
        dishForm.setName("duy");
        dishForm.setStatus(DishStatus.NOT_AVAILABLE);
//        dishForm.setPrice(1);

        HttpEntity<DishForm> entity = new HttpEntity<>(dishForm);
        Dish dish = this.restTemplate.exchange("/dishes", HttpMethod.POST, entity, Dish.class).getBody();

        Assertions.assertThat(dish).isNotNull();
        Assertions.assertThat(dish.getId()).isNotNull();
        Assertions.assertThat(dish.getName()).isEqualTo(dishForm.getName());
        Assertions.assertThat(dish.getStatus()).isEqualTo(dishForm.getStatus());
        Assertions.assertThat(dish.getPrice()).isEqualTo(dishForm.getPrice());


        DishForm form= new DishForm();
        form.getName();
        form.getStatus();
        form.getPrice();

        HttpEntity<DishForm> reEntity = new HttpEntity<>(form);
        Dish dishes = this.restTemplate.exchange("/dishes/3", HttpMethod.GET, reEntity, Dish.class).getBody();

        Assertions.assertThat(dishes).isNotNull();
        Assertions.assertThat(dishes.getId()).isNotNull();
        Assertions.assertThat(dishes.getName()).isEqualTo("3");
        Assertions.assertThat(dishes.getStatus()).isEqualTo(AVAILABLE);
        Assertions.assertThat(dishes.getPrice()).isEqualTo(3);
    }
    @Test
    public void testGetAll(){
        ParameterizedTypeReference<List<Dish>>  responseType = new ParameterizedTypeReference<List<Dish>>() {
        };

        List<Dish> dishes = this.restTemplate.exchange("/dishes",HttpMethod.GET,null,responseType).getBody();
        Assertions.assertThat(dishes).hasSize(5);
    }
    @Test
    public void testDelete(){
        DishForm dishForm = new DishForm();
        HttpEntity<DishForm> entity = new HttpEntity<>(dishForm);
        this.restTemplate.exchange("/dishes/12",HttpMethod.DELETE,entity,Dish.class).getBody();
    }

    @Test
    public void testUpdate(){
        DishForm dishForm = new DishForm();
        dishForm.setName("huy");
        dishForm.setStatus(NOT_AVAILABLE);
//        dishForm.setPrice(4);

        HttpEntity<DishForm> entity = new HttpEntity<>(dishForm);

        Dish dish = this.restTemplate.exchange("/dishes/3",HttpMethod.POST,entity,Dish.class).getBody();

        Assertions.assertThat(dish).isNotNull();
        Assertions.assertThat(dish.getId()).isNotNull();
        Assertions.assertThat(dish.getName()).isEqualTo(dishForm.getName());
        Assertions.assertThat(dish.getStatus()).isEqualTo(dishForm.getStatus());
        Assertions.assertThat(dish.getPrice()).isEqualTo(dishForm.getPrice());
    }
}
