package com.belenot.eatfood.integrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.net.URI;

import com.belenot.eatfood.web.form.CreateFoodForm;
import com.belenot.eatfood.web.form.SignUpForm;
import com.belenot.eatfood.web.model.ClientModel;
import com.belenot.eatfood.web.model.FoodModel;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// @ContextConfiguration(classes = {EatfoodApplication.class})
// @WebAppConfiguration
// TEST ISN'T EXECUTED. WHY? I DONT KNOW. IT HAS BECOME BORED TO CATCH PITFALLS. 
// BETER DO THAT KIND OF TESTING IN POSTMAN
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class ClientActionTest1 {
    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mvc;
    @Autowired
    private TestRestTemplate restTemplate;
    @BeforeAll
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }
    @Test
    @Commit
    @Order(1)
    public void clientShouldConnect() {
        ResponseEntity<String> response = restTemplate.getForEntity("/eatfood", String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    @Commit
    @Order(2)
    public void clientShouldSignUp() {
        // String clientString = "{\"login\":\"login1\",\"password\":\"password1\",\"name\":\"name1\"}";
        SignUpForm form = new SignUpForm();
        form.setLogin("login1"); form.setPassword("password1"); form.setName("name1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<SignUpForm> request = new HttpEntity<>(form, headers);
        ResponseEntity<ClientModel> response = restTemplate.postForEntity("/client/signup", request, ClientModel.class);
        // String clientModelString = restTemplate.postForObject("http://localhost:8082/client/signup", request, String.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals("login1", response.getBody().getLogin());
        assertEquals("name1", response.getBody().getName());
        assertTrue(response.getBody().getId() > 0);
    }

    @Test
    @Order(3)
    public void clientShouldRequestMe() {
        ResponseEntity<ClientModel> response = restTemplate.getForEntity("/client/me", ClientModel.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals("login1", response.getBody().getLogin());
        assertEquals("name1", response.getBody().getName());
        assertTrue(response.getBody().getId() > 0);
    }

    @Test
    @Order(4)
    public void clientShouldCreateFood() throws Exception {
        CreateFoodForm form = new CreateFoodForm();
        form.setName("food1");
        form.setKcal(new BigDecimal(1));
        form.setProt(new BigDecimal(1));
        form.setCarb(new BigDecimal(1));
        form.setFat(new BigDecimal(1));
        RequestEntity<CreateFoodForm> request = RequestEntity.post(new URI("/food/create")).body(form);
        ResponseEntity<FoodModel> response = restTemplate.postForEntity("/food/create", request, FoodModel.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getId() > 0);
        assertEquals("food1", response.getBody().getName());
        assertEquals(new BigDecimal(1), response.getBody().getKcal());
        assertEquals(new BigDecimal(1), response.getBody().getProt());
        assertEquals(new BigDecimal(1), response.getBody().getCarb());
        assertEquals(new BigDecimal(1), response.getBody().getFat());
    }

}