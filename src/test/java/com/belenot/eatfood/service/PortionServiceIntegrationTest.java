package com.belenot.eatfood.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import com.belenot.eatfood.domain.Client;
import com.belenot.eatfood.domain.Portion;
import com.belenot.eatfood.repository.ClientRepository;
import com.belenot.eatfood.repository.PortionRepository;
import com.belenot.eatfood.repository.support.OffsetPageable;
import com.belenot.eatfood.service.support.PortionFilter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("write-portions.sql")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class PortionServiceIntegrationTest {

    @Autowired
    private PortionService portionService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PortionRepository portionRepository;
    private long portionCount = 15; // taken from write-portions.sql

    @Test
    public void shouldGetByFilter_all() {
        Client client = clientRepository.findById(1L).get();
        PortionFilter filter = PortionFilter.builder().client(client).date(LocalDate.of(2010, 1, 1), LocalDate.of(2020, 1, 1)).gram(0, 200).build();
        Pageable page = new OffsetPageable(0, 1000, 1);
        
        List<Portion> portions = assertDoesNotThrow(()->portionService.byFilter(client, filter, page));

        assertNotNull(portions);
        assertEquals(portionCount, portions.size());
    }


}