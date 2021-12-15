package com.onlinestore.services;

import com.onlinestore.entities.Product;
import com.onlinestore.entities.ProductQuantity;
import com.onlinestore.exceptions.ResourceMissingInDatabase;
import com.onlinestore.repositories.ProductQuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductQuantityServiceImplementation implements ProductQuantityService{

    private ProductQuantityRepository productQuantityRepository;

    @Autowired
    public ProductQuantityServiceImplementation(ProductQuantityRepository productQuantityRepository) {
        this.productQuantityRepository = productQuantityRepository;
    }


    @Override
    public ProductQuantity save(ProductQuantity productQuantity) {
        return productQuantityRepository.save(productQuantity);
    }

    @Override
    public ProductQuantity findById(Long id) {
        Optional<ProductQuantity> productOptional = productQuantityRepository.findById(id);
        if (!productOptional.isPresent()) {
            throw new ResourceMissingInDatabase(String.format("Product with id = %d not found", id));
        }
        return productOptional.get();
    }

    @Override
    public ProductQuantity update(ProductQuantity productQuantity) {
        return productQuantityRepository.save(productQuantity);
    }
}
