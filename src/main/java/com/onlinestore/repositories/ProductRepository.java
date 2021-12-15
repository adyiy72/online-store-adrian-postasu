package com.onlinestore.repositories;

import com.onlinestore.entities.Category;
import com.onlinestore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {


    @Query("FROM Product ORDER BY price ASC")
    List<Product> sortProductsByPrice ();

    List<Product> findAllByCategory(Category category);
}
