package com.charuniverse.springjpa.service;

import com.charuniverse.springjpa.entity.Category;
import com.charuniverse.springjpa.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionOperations;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionOperations transactionOperations;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public void error() {
        throw new RuntimeException("Ups rollback please!");
    }

    public void manual() {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setTimeout(10);
        definition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
        definition.setIsolationLevel(DefaultTransactionDefinition.ISOLATION_DEFAULT);

        TransactionStatus transactionStatus = transactionManager.getTransaction(definition);
        try {
            for (int i = 0; i < 5; i++) {
                Category category = new Category();
                category.setName("Category " + i);
                categoryRepository.save(category);
            }
            error();
            transactionManager.commit(transactionStatus);
        } catch (Throwable throwable) {
            transactionManager.rollback(transactionStatus);
            throw throwable;
        }
    }

    public void createCategories() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            for (int i = 0; i < 5; i++) {
                Category category = new Category();
                category.setName("Category " + i);
                categoryRepository.save(category);
            }
            error();
        });
    }

    @Transactional
    public void create() {
        for (int i = 0; i < 5; i++) {
            Category category = new Category();
            category.setName("Category " + i);
            categoryRepository.save(category);
        }
        error();
    }

    public void runCreate() {
        create();
    }

}
