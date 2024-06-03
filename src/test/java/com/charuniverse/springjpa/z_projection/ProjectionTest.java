package com.charuniverse.springjpa.z_projection;

import com.charuniverse.springjpa.model.ProductPrice;
import com.charuniverse.springjpa.model.SimpleProduct;
import com.charuniverse.springjpa.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProjectionTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void projection() {
        List<SimpleProduct> simpleProducts = productRepository.findAllByNameLike("%Apple%", SimpleProduct.class);
        Assertions.assertEquals(2, simpleProducts.size());
    }

    @Test
    void projection2() {
        List<ProductPrice> simpleProducts = productRepository.findAllByNameLike("%Apple%", ProductPrice.class);
        Assertions.assertEquals(2, simpleProducts.size());
    }
}
