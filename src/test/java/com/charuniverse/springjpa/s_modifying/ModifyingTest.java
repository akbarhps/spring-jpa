package com.charuniverse.springjpa.s_modifying;

import com.charuniverse.springjpa.entity.Product;
import com.charuniverse.springjpa.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionOperations;

@SpringBootTest
public class ModifyingTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionOperations transactionOperations;

    @Test
    void modifying() {
        transactionOperations.executeWithoutResult(status -> {
            int total = productRepository.deleteProductUsingName("Awek");
            Assertions.assertEquals(0, total);

            total = productRepository.updateProductPriceToZero(1L);
            Assertions.assertEquals(1, total);

            Product product = productRepository.findById(1L).orElse(null);
            Assertions.assertNotNull(product);
            Assertions.assertEquals(0, product.getPrice());
        });
    }
}
