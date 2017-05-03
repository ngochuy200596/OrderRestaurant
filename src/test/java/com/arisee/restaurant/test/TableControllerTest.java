package com.arisee.restaurant.test;

import com.arisee.restaurant.Application;
import com.arisee.restaurant.domain.table.Table;
import com.arisee.restaurant.domain.user.User;
import com.arisee.restaurant.model.table.TableForm;
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


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class TableControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreate(){
        TableForm tableForm = new TableForm();
        tableForm.setName("ban2");
        tableForm.setLocation("4");

        HttpEntity<TableForm> entity = new HttpEntity<>(tableForm);
        Table table = this.restTemplate.exchange("/tables", HttpMethod.POST, entity, Table.class).getBody();

        Assertions.assertThat(table).isNotNull();
        Assertions.assertThat(table.getId()).isNotNull();
        Assertions.assertThat(table.getName()).isEqualTo(tableForm.getName());
        Assertions.assertThat(table.getLocation()).isEqualTo(tableForm.getLocation());

        TableForm tableForm1 = new TableForm();
        tableForm1.getName();
        tableForm1.getLocation();

        HttpEntity<TableForm> reEntity = new HttpEntity<>(tableForm);
        Table tables = this.restTemplate.exchange("/tables/1",HttpMethod.GET, reEntity, Table.class).getBody();

        Assertions.assertThat(tables).isNotNull();
        Assertions.assertThat(tables.getId()).isNotNull();
        Assertions.assertThat(tables.getName()).isEqualTo("1");
        Assertions.assertThat(tables.getLocation()).isEqualTo("1");
    }

    @Test
    public void testGetAll(){
        ParameterizedTypeReference<List<Table>> responseType = new ParameterizedTypeReference<List<Table>>() {
        };
        List<Table> tables = this.restTemplate.exchange("/tables",HttpMethod.GET,null,responseType).getBody();
        Assertions.assertThat(tables).hasSize(6);
    }
    @Test
    public void testDelete(){
        TableForm tableForm = new TableForm();
        HttpEntity<TableForm> entity = new HttpEntity<>(tableForm);
        this.restTemplate.exchange("/tables/8",HttpMethod.DELETE,entity,Table.class).getBody();
    }

    @Test
    public void testUpdate(){
        TableForm tableForm = new TableForm();
        tableForm.setName("demo");
        tableForm.setLocation("demo");

        HttpEntity<TableForm> entity =new HttpEntity<>(tableForm);
        Table table = this.restTemplate.exchange("/tables/1",HttpMethod.POST,entity,Table.class).getBody();

        Assertions.assertThat(table).isNotNull();
        Assertions.assertThat(table.getId()).isNotNull();
        Assertions.assertThat(table.getName()).isEqualTo(tableForm.getName());
        Assertions.assertThat(table.getLocation()).isEqualTo(tableForm.getLocation());
    }

}
