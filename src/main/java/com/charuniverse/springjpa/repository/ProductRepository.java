package com.charuniverse.springjpa.repository;

import com.charuniverse.springjpa.entity.Category;
import com.charuniverse.springjpa.entity.Product;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    boolean existsByName(String name);

    Long countByCategory_Name(String name);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findFirstById(Long id);

    <T> List<T> findAllByNameLike(String name, Class<T> tClass);

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
