package com.onlinestore.repositories;

import com.onlinestore.entities.Product;
import com.onlinestore.entities.ProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductQuantityRepository extends JpaRepository<ProductQuantity, Long> {
    List<ProductQuantity> findAllByProduct(Product product);
}
