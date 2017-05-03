package com.arisee.restaurant.test;

import com.arisee.restaurant.Application;
import com.arisee.restaurant.domain.category.Category;
import com.arisee.restaurant.model.category.CategoryForm;
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
public class CategoryControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

   @Test
    public void testCreate(){
       CategoryForm categoryForm = new CategoryForm();
       categoryForm.setName("duy");
       HttpEntity<CategoryForm> entity = new HttpEntity<>(categoryForm);

       Category category = this.restTemplate.exchange("/categories",HttpMethod.POST,entity,Category.class).getBody();

       Assertions.assertThat(category).isNotNull();
       Assertions.assertThat(category.getId()).isNotNull();
       Assertions.assertThat(category.getName()).isEqualTo(categoryForm.getName());

       CategoryForm form = new CategoryForm();
       form.getName();
       HttpEntity<CategoryForm> reEntity = new HttpEntity<>(form);

       Category categories = this.restTemplate.exchange("/categories/1",HttpMethod.GET,reEntity,Category.class).getBody();
       Assertions.assertThat(categories).isNotNull();
       Assertions.assertThat(categories.getId()).isNotNull();
       Assertions.assertThat(categories.getName()).isEqualTo("1");


   }

   @Test
    public void testGetAll(){
        ParameterizedTypeReference<List<Category>> responseType= new ParameterizedTypeReference<List<Category>>() {
        };
        List<Category> categories = this.restTemplate
                .exchange("/categories",HttpMethod.GET,null,responseType).getBody();
        Assertions.assertThat(categories).hasSize(8);
   }

   @Test
    public void testDelete(){
        CategoryForm categoryForm = new CategoryForm();
       HttpEntity<CategoryForm> entity = new HttpEntity<>(categoryForm);
       this.restTemplate.exchange("/categories/9",HttpMethod.DELETE,entity,Category.class).getBody();
   }

   @Test
    public void testUpdate(){
        CategoryForm categoryForm = new CategoryForm();
        categoryForm.setName("huy");

        HttpEntity<CategoryForm> entity = new HttpEntity<>(categoryForm);
        Category category = this.restTemplate.exchange("/categories/1",HttpMethod.POST,entity,Category.class).getBody();
        Assertions.assertThat(category).isNotNull();
        Assertions.assertThat(category.getId()).isNotNull();
        Assertions.assertThat(category.getName()).isEqualTo(categoryForm.getName());


   }
}
