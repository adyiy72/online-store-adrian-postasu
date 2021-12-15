package com.onlinestore.controllers;

import com.onlinestore.entities.Category;
import com.onlinestore.exceptions.CategoryAssociatedProductsException;
import com.onlinestore.services.CategoryService;
import com.onlinestore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Category findById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        if(productService.findAllByCategory(categoryService.findById(id)).size() > 0) {
            throw new CategoryAssociatedProductsException("The category is associated to products.");
        }
        categoryService.delete(id);
        return HttpStatus.OK;
    }

    @PutMapping(path = "/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category){
        category.setId(id);
        return categoryService.update(category);
    }
}
