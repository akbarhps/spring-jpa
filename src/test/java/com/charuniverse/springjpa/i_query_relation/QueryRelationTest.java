package com.charuniverse.springjpa.i_query_relation;

import com.charuniverse.springjpa.entity.Category;
import com.charuniverse.springjpa.entity.Product;
import com.charuniverse.springjpa.repository.CategoryRepository;
import com.charuniverse.springjpa.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QueryRelationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void createProduct() {
        Category category = categoryRepository.findFirstByName("Electronics").orElse(null);
        Assertions.assertNotNull(category);

        {
            Product product = new Product();
            product.setName("Apple iPhone 14 Pro Max");
            product.setPrice(25_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }
        {
            Product product = new Product();
            product.setName("Apple iPhone 13 Pro Max");
            product.setPrice(18_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }
    }

    @Test
    void findProducts() {
        List<Product> products = productRepository.findAllByCategory_Name("Electronics");
        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Apple iPhone 14 Pro Max", products.get(0).getName());
        Assertions.assertEquals("Apple iPhone 13 Pro Max", products.get(1).getName());
    }
}
