package com.onlinestore.services;

import com.onlinestore.entities.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);
    List<Category> findAll();
    void delete(Long id);
    Category findById(Long id);
    Category update(Category category);

}
