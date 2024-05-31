package com.charuniverse.springjpa.f_declarative_transaction;

import com.charuniverse.springjpa.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeclarativeTransactionTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void testSuccess() {
        Assertions.assertThrows(RuntimeException.class, () -> categoryService.create());
    }

    @Test
    void testFailed() {
        Assertions.assertThrows(RuntimeException.class, () -> categoryService.runCreate());
    }
}
