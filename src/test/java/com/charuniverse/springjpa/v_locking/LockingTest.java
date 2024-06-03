package com.charuniverse.springjpa.v_locking;

import com.charuniverse.springjpa.entity.Product;
import com.charuniverse.springjpa.repository.CategoryRepository;
import com.charuniverse.springjpa.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionOperations;

@SpringBootTest
public class LockingTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionOperations transactionOperations;

    @Test
    void lock1() {
        transactionOperations.executeWithoutResult(status -> {
            try {
                Product product = productRepository.findFirstById(1L).orElse(null);
                Assertions.assertNotNull(product);
                product.setPrice(30_000_000L);

                Thread.sleep(20_000L);
                productRepository.save(product);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void lock2() {
        transactionOperations.executeWithoutResult(status -> {
            Product product = productRepository.findFirstById(1L).orElse(null);
            Assertions.assertNotNull(product);

            product.setPrice(10_000_000L);
            productRepository.save(product);
        });
    }
}
