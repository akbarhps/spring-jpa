package com.charuniverse.springjpa.e_crud_repository;

import com.charuniverse.springjpa.entity.Category;
import com.charuniverse.springjpa.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrudRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testCreate() {
        Category category = new Category();
        category.setName("Electronics");

        categoryRepository.save(category);
        Assertions.assertNotNull(category.getId());
    }

    @Test
    void testUpdate() {
        Category category = categoryRepository.findById(26L).orElse(null);
        Assertions.assertNotNull(category);

        category.setName("Electronics Terbaru");
        categoryRepository.save(category);

        category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);
        Assertions.assertEquals("Electronics Terbaru", category.getName());
    }
}
