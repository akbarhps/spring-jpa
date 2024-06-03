package com.charuniverse.springjpa.l_page_result;

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
public class PageResultTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findProductsPaging() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by("id").descending());
        Page<Product> products = productRepository.findAllByCategory_Name("Electronics", pageable);

        Assertions.assertEquals(1, products.getContent().size());
        Assertions.assertEquals(0, products.getNumber());
        Assertions.assertEquals(2, products.getTotalElements());
        Assertions.assertEquals(2, products.getTotalPages());

        Assertions.assertEquals("Apple iPhone 13 Pro Max", products.getContent().get(0).getName());
    }

    @Test
    void findProductsPaging2() {
        Pageable pageable = PageRequest.of(1, 1, Sort.by("id").descending());
        Page<Product> products = productRepository.findAllByCategory_Name("Electronics", pageable);

        Assertions.assertEquals(1, products.getContent().size());
        Assertions.assertEquals(1, products.getNumber());
        Assertions.assertEquals(2, products.getTotalElements());
        Assertions.assertEquals(2, products.getTotalPages());

        Assertions.assertEquals("Apple iPhone 14 Pro Max", products.getContent().get(0).getName());
    }
}
