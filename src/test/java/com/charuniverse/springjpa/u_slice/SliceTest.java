package com.charuniverse.springjpa.u_slice;

import com.charuniverse.springjpa.entity.Category;
import com.charuniverse.springjpa.entity.Product;
import com.charuniverse.springjpa.repository.CategoryRepository;
import com.charuniverse.springjpa.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@SpringBootTest
public class SliceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void slice() {
        Pageable firstPage = PageRequest.of(0, 1);

        Category category = categoryRepository.findById(36L).orElse(null);
        Assertions.assertNotNull(category);

        Slice<Product> slice = productRepository.findALlByCategory(category, firstPage);
        while (slice.hasNext()) {
            slice = productRepository.findALlByCategory(category, slice.nextPageable());
        }
    }
}
