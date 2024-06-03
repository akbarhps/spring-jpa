package com.charuniverse.springjpa.n_exists_query_method;

import com.charuniverse.springjpa.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExistsQueryMethodTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void existsQuery() {
        boolean exists = productRepository.existsByName("Laptop");
        Assertions.assertFalse(exists);

        exists = productRepository.existsByName("Apple iPhone 13 Pro Max");
        Assertions.assertTrue(exists);
    }
}
