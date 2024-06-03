package com.charuniverse.springjpa.o_delete_query_method;

import com.charuniverse.springjpa.entity.Category;
import com.charuniverse.springjpa.entity.Product;
import com.charuniverse.springjpa.repository.CategoryRepository;
import com.charuniverse.springjpa.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionOperations;

@SpringBootTest
public class DeleteQueryMethodTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionOperations transactionOperations;

    @Test
    void deleteQuery() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Category category = categoryRepository.findFirstByName("Electronics").orElse(null);
            Assertions.assertNotNull(category);

            Product product = new Product();
            product.setName("Promag");
            product.setPrice(20_000L);
            product.setCategory(category);
            productRepository.save(product);

            int rowsDeleted = productRepository.deleteByName(product.getName());
            Assertions.assertEquals(1, rowsDeleted);

            rowsDeleted = productRepository.deleteByName(product.getName());
            Assertions.assertEquals(0, rowsDeleted);
        });
    }
}
