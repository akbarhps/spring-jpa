package com.charuniverse.springjpa.y_specification;

import com.charuniverse.springjpa.entity.Product;
import com.charuniverse.springjpa.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@SpringBootTest
public class SpecificationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void specification() {
        Specification<Product> specification = (root, criteriaQuery, criteriaBuilder) -> criteriaQuery.where(
                criteriaBuilder.or(
                        criteriaBuilder.equal(root.get("name"), "Apple iPhone 13 Pro Max"),
                        criteriaBuilder.equal(root.get("name"), "Apple iPhone 14 Pro Max")
                )
        ).getRestriction();

        List<Product> products = productRepository.findAll(specification);
        Assertions.assertEquals(2, products.size());
    }
}
