package com.charuniverse.springjpa.m_count_query_method;

import com.charuniverse.springjpa.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CountQueryMethodTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void countQuery() {
        Long count = productRepository.count();
        Assertions.assertEquals(2L, count);

        count = productRepository.countByCategory_Name("Electronics");
        Assertions.assertEquals(2L, count);
    }
}
