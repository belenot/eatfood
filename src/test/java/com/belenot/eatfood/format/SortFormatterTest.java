package com.belenot.eatfood.format;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.data.domain.Sort;

@TestInstance(Lifecycle.PER_CLASS)

public class SortFormatterTest {
    private SortFormatter formatter;
    @BeforeAll
    public void init() {
        formatter = new SortFormatter();
    }
    @Test
    public void formatTest_1() {
        String sortString = "gram,date,portion";

        Sort sort = assertDoesNotThrow(()->formatter.parse(sortString, Locale.getDefault()));

        assertNotNull(sort);
        assertTrue(sort.isSorted());
    }

    @Test
    public void formatTest_2() {
        String sortString = "gram,!date,portion";

        Sort sort = assertDoesNotThrow(()->formatter.parse(sortString, Locale.getDefault()));

        assertNotNull(sort);
        assertTrue(sort.isSorted());
        assertTrue(sort.getOrderFor("date").isDescending());
    }

}