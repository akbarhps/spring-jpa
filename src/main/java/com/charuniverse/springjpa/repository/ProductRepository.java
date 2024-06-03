package com.charuniverse.springjpa.repository;

import com.charuniverse.springjpa.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    int deleteByName(String name);

    boolean existsByName(String name);

    Long countByCategory_Name(String name);

    List<Product> findAllByCategory_Name(String name);

    List<Product> findAllByCategory_Name(String name, Sort sort);

    List<Product> searchProductUsingName(@Param("name") String name, Pageable pageable);

    Page<Product> findAllByCategory_Name(String name, Pageable pageable);

}
