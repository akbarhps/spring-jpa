package com.charuniverse.springjpa.repository;

import com.charuniverse.springjpa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // SELECT * FROM category WHERE name = ?
    Optional<Category> findFirstByName(String name);

    // SELECT * FROM category WHERE name LIKE ?
    List<Category> findAllByNameLike(String name);

}
