package com.charuniverse.springjpa.repository;

import com.charuniverse.springjpa.entity.Category;
import com.charuniverse.springjpa.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    Long countByCategory_Name(String name);

    List<Product> findAllByCategory_Name(String name);

    List<Product> findAllByCategory_Name(String name, Sort sort);

    List<Product> searchProductUsingName(@Param("name") String name, Pageable pageable);

    @Query(
            value = "SELECT p FROM Product p WHERE p.name LIKE :name OR p.category.name LIKE :name",
            countQuery = "SELECT COUNT(p) FROM Product p WHERE p.name LIKE :name OR p.category.name LIKE :name"
    )
    Page<Product> searchProduct(@Param("name") String name, Pageable pageable);

    Page<Product> findAllByCategory_Name(String name, Pageable pageable);

    Stream<Product> streamAllByCategory(Category category);

    Slice<Product> findALlByCategory(Category category, Pageable pageable);

    @Transactional
    int deleteByName(String name);

    @Modifying
    @Query("DELETE FROM Product p WHERE p.name = :name")
    int deleteProductUsingName(@Param("name") String name);

    @Modifying
    @Query("UPDATE Product p SET p.price = 0 WHERE p.id = :id")
    int updateProductPriceToZero(@Param("id") Long id);

}
