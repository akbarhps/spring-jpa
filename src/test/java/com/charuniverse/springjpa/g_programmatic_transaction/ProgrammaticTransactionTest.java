package com.charuniverse.springjpa.g_programmatic_transaction;

import com.charuniverse.springjpa.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProgrammaticTransactionTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void testProgrammatic() {
        Assertions.assertThrows(RuntimeException.class, () -> categoryService.createCategories());
    }

    @Test
    void testProgrammaticManual() {
        Assertions.assertThrows(RuntimeException.class, () -> categoryService.manual());
    }
}
