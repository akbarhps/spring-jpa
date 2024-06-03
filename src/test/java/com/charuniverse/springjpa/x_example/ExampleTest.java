package com.charuniverse.springjpa.x_example;

import com.charuniverse.springjpa.entity.Category;
import com.charuniverse.springjpa.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

@SpringBootTest
public class ExampleTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void example1() {
        Category category = new Category();
        category.setName("Contoh auditorium");

        Example<Category> example = Example.of(category);

        List<Category> categories = categoryRepository.findAll(example);
        Assertions.assertEquals(1, categories.size());
    }

    @Test
    void example2() {
        Category category = new Category();
        category.setName("Contoh auditorium");
        category.setId(58L);

        Example<Category> example = Example.of(category);

        List<Category> categories = categoryRepository.findAll(example);
        Assertions.assertEquals(1, categories.size());
    }

    @Test
    void exampleMatcher() {
        Category category = new Category();
        category.setName("contoh auditorium");

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase();
        Example<Category> example = Example.of(category, exampleMatcher);

        List<Category> categories = categoryRepository.findAll(example);
        Assertions.assertEquals(1, categories.size());
    }
}
