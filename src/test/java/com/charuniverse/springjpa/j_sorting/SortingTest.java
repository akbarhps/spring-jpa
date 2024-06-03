package com.charuniverse.springjpa.j_sorting;

import com.charuniverse.springjpa.entity.Product;
import com.charuniverse.springjpa.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class SortingTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findProductsSort() {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        List<Product> products = productRepository.findAllByCategory_Name("Electronics", sort);

        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Apple iPhone 13 Pro Max", products.get(0).getName());
        Assertions.assertEquals("Apple iPhone 14 Pro Max", products.get(1).getName());
    }
}
