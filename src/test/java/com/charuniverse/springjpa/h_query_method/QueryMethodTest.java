package com.charuniverse.springjpa.h_query_method;

import com.charuniverse.springjpa.entity.Category;
import com.charuniverse.springjpa.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QueryMethodTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    void testQueryMethod() {
        Category category = repository.findFirstByName("Electronics").orElse(null);
        Assertions.assertNotNull(category);
        Assertions.assertEquals("Electronics", category.getName());

        List<Category> categories = repository.findAllByNameLike("%Electronics%");
        Assertions.assertFalse(categories.isEmpty());
    }
}
