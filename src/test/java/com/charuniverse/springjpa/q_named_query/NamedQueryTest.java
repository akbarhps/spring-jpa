package com.charuniverse.springjpa.q_named_query;

import com.charuniverse.springjpa.entity.Product;
import com.charuniverse.springjpa.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
public class NamedQueryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void namedQuery() {
        String productName = "Apple iPhone 13 Pro Max";
        Pageable pageable = PageRequest.of(0, 1);
        List<Product> products = productRepository.searchProductUsingName(productName, pageable);

        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals(productName, products.get(0).getName());
    }
}
