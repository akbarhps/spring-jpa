package com.charuniverse.springjpa.w_auditing;

import com.charuniverse.springjpa.entity.Category;
import com.charuniverse.springjpa.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuditingTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void audit() {
        Category category = new Category();
        category.setName("Contoh auditorium");
        categoryRepository.save(category);

        Assertions.assertNotNull(category.getId());
        Assertions.assertNotNull(category.getCreatedDate());
        Assertions.assertNotNull(category.getLastModifiedDate());
    }
}
