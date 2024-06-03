package com.charuniverse.springjpa.r_query_annotation;

import com.charuniverse.springjpa.entity.Product;
import com.charuniverse.springjpa.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
public class QueryAnnotationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void queryAnnotation() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by("id").descending());

        Page<Product> products = productRepository.searchProduct("%iPhone%", pageable);
        Assertions.assertEquals(1, products.getSize());
        Assertions.assertEquals(0, products.getNumber());
        Assertions.assertEquals(2, products.getTotalPages());
        Assertions.assertEquals(2, products.getTotalElements());

        products = productRepository.searchProduct("%Electronics%", pageable);
        Assertions.assertEquals(1, products.getSize());
        Assertions.assertEquals(0, products.getNumber());
        Assertions.assertEquals(2, products.getTotalPages());
        Assertions.assertEquals(2, products.getTotalElements());
    }
}
