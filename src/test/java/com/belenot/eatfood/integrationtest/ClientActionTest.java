package com.belenot.eatfood.integrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.belenot.eatfood.web.form.CreateFoodForm;
import com.belenot.eatfood.web.form.CreatePortionForm;
import com.belenot.eatfood.web.form.PortionFilterForm;
import com.belenot.eatfood.web.form.SignUpForm;
import com.belenot.eatfood.web.form.UpdatePortionForm;
import com.belenot.eatfood.web.model.ClientModel;
import com.belenot.eatfood.web.model.FoodModel;
import com.belenot.eatfood.web.model.PortionModel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;
import org.springframework.web.context.WebApplicationContext;

// @ContextConfiguration(classes = {EatfoodApplication.class})
// @WebAppConfiguration
// TEST ISN'T EXECUTED. WHY? I DONT KNOW. IT HAS BECOME BORED TO CATCH PITFALLS. 
// BETER DO THAT KIND OF TESTING IN POSTMAN
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ClientActionTest {
    @Autowired
    private WebApplicationContext wac;
    
    private List<String> cookies = new ArrayList<>();
    private Long foodId = 0L;
    private Long portionId = 0L;
    private Long newPortionId = 0L;
    @Autowired
    private TestRestTemplate restTemplate;
    @BeforeAll
 
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
        cookies = new ArrayList<>(response.getHeaders().get("Set-Cookie"));
        
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals("login1", response.getBody().getLogin());
        assertEquals("name1", response.getBody().getName());
        assertTrue(response.getBody().getId() > 0);
    }

    @Test
    @Order(3)
    public void clientShouldRequestMe() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Cookie", cookies);
        RequestEntity<String> request = new RequestEntity<String>(headers, HttpMethod.GET, URI.create("/client/me"));
        ResponseEntity<ClientModel> response = restTemplate.exchange(request, ClientModel.class);
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

        HttpHeaders headers = new HttpHeaders();
        headers.put("Cookie", cookies);
        RequestEntity<CreateFoodForm> request = new RequestEntity<>(form, headers, HttpMethod.POST, URI.create("/food/create"));
        ResponseEntity<FoodModel> response = restTemplate.postForEntity("/food/create", request, FoodModel.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getId() > 0);
        foodId = response.getBody().getId();
        assertEquals("food1", response.getBody().getName());
        assertEquals(new BigDecimal(1), response.getBody().getKcal());
        assertEquals(new BigDecimal(1), response.getBody().getProt());
        assertEquals(new BigDecimal(1), response.getBody().getCarb());
        assertEquals(new BigDecimal(1), response.getBody().getFat());
    }

    @Test
    @Order(5)
    public void clientShouldCreatePortion() throws Exception {
        CreatePortionForm form = new CreatePortionForm();
        LocalDate date = LocalDate.now();
        form.setDate(date);
        form.setFoodId(foodId);
        form.setGram(BigDecimal.valueOf(100));

        HttpHeaders headers = new HttpHeaders();
        headers.put("Cookie", cookies);
        RequestEntity<CreatePortionForm> request = new RequestEntity<>(form, headers, HttpMethod.POST, URI.create("/portion/create"));
        ResponseEntity<PortionModel> response = restTemplate.exchange(request, PortionModel.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        portionId = response.getBody().getId();
        assertTrue(response.getBody().getId() > 0);
        assertEquals( foodId, response.getBody().getFoodId());
        assertEquals(date, response.getBody().getDate());
        assertEquals(BigDecimal.valueOf(100).setScale(2), response.getBody().getGram());
    }

    @Test
    @Order(6)
    public void clientShouldUpdatePortion() throws Exception {
        UpdatePortionForm form = new UpdatePortionForm();
        LocalDate newDate = LocalDate.now().plusDays(15);
        form.setDate(newDate);
        form.setGram(BigDecimal.valueOf(150));

        HttpHeaders headers = new HttpHeaders();
        headers.put("Cookie", cookies);
        RequestEntity<UpdatePortionForm> request = new RequestEntity<>(form, headers, HttpMethod.POST, URI.create("/portion/"+portionId+"/update"));
        ResponseEntity<PortionModel> response = restTemplate.exchange(request, PortionModel.class);

        assertNotNull(response);
        assertNotNull(response.getBody().getId());
        assertTrue(response.getBody().getId() > 0);
        assertTrue(response.getBody().getFoodId() > 0);
        assertEquals(newDate, response.getBody().getDate());
        assertEquals(BigDecimal.valueOf(150).setScale(2), response.getBody().getGram());
    }

    @Test
    @Order(7)
    public void clientShouldRemovePortion() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Cookie", cookies);
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.POST, URI.create("/portion/"+portionId+"/delete"));
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        assertNotNull(response);
        assertEquals("true", response.getBody());
    }

    @Test
    @Order(8)
    public void clientShouldCreateNewPortion() throws Exception {
        CreatePortionForm form = new CreatePortionForm();
        LocalDate date = LocalDate.now();
        form.setDate(date);
        form.setFoodId(foodId);
        form.setGram(BigDecimal.valueOf(250));

        HttpHeaders headers = new HttpHeaders();
        headers.put("Cookie", cookies);
        RequestEntity<CreatePortionForm> request = new RequestEntity<>(form, headers, HttpMethod.POST, URI.create("/portion/create"));
        ResponseEntity<PortionModel> response = restTemplate.exchange(request, PortionModel.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        newPortionId = response.getBody().getId();
        assertTrue(newPortionId > portionId);
        assertEquals( foodId, response.getBody().getFoodId());
        assertEquals(date, response.getBody().getDate());
        assertEquals(BigDecimal.valueOf(250).setScale(2), response.getBody().getGram());
    }

    @Test
    @Order(9)
    public void clientShouldFetchPortionByFilter() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Cookie", cookies);
        String afterDate="2010-10-10";
        String beforeDate="2020-10-10";
        String gteGram="50";
        String lteGram="300";
        String offset="0";
        String page="1";
        String size="10";
        String url = String.format("/portion/get/filter?afterDate=%s&beforeDate=%s&gteGram=%s&lteGram=%s&page=%s&size=%s&offset=%s",
        afterDate, beforeDate, gteGram, lteGram, page, size, offset
        );

        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length() > 0);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        // think about how check filtered result
    }

    // @Test
    // @Order(10)
    // public void clientShouldDelete

}