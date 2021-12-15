package com.onlinestore.services;

import com.onlinestore.entities.ProductQuantity;

public interface ProductQuantityService {

        ProductQuantity save(ProductQuantity productQuantity);
        ProductQuantity findById(Long id);
        ProductQuantity update(ProductQuantity productQuantity);


}
