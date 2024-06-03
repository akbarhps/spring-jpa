package com.charuniverse.springjpa.k_paging;

import com.charuniverse.springjpa.entity.Product;
import com.charuniverse.springjpa.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PagingTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findProductsPaging() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by("id").ascending());
        List<Product> products = productRepository.findAllByCategory_Name("Electronics", pageable);

        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals("Apple iPhone 14 Pro Max", products.get(0).getName());
    }

    @Test
    void findProductsPaging2() {
        Pageable pageable = PageRequest.of(1, 1, Sort.by("id").ascending());
        List<Product> products = productRepository.findAllByCategory_Name("Electronics", pageable);

        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals("Apple iPhone 13 Pro Max", products.get(0).getName());
    }
}
