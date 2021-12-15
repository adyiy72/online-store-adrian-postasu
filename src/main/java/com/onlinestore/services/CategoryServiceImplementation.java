package com.onlinestore.services;

import com.onlinestore.entities.Category;
import com.onlinestore.exceptions.ResourceMissingInDatabase;
import com.onlinestore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) {
            throw new ResourceMissingInDatabase(String.format("Category with id %d is missing", id));
        }
        return categoryOptional.get();
    }

    @Override
    public Category update(Category category) {
        Optional<Category> categoryDbOptional = categoryRepository.findById(category.getId());
        if (!categoryDbOptional.isPresent()) {
            throw new ResourceMissingInDatabase(String.format("Category with id %d is missing", category.getId()));
        }
        Category categoryDb = categoryDbOptional.get();
        categoryDb.setName(category.getName());
        categoryDb.setDescription(category.getDescription());
        return categoryRepository.save(categoryDb);
    }
}
