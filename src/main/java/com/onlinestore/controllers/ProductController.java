package com.onlinestore.controllers;

import com.onlinestore.controllers.dto.ProductDto;
import com.onlinestore.entities.Category;
import com.onlinestore.entities.Product;
import com.onlinestore.services.CategoryService;
import com.onlinestore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService,
                             CategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public Product save(@RequestBody ProductDto productDto) {
        Product product = ProductDto.toProduct(productDto, categoryService);
        return productService.save(product);
    }

    // http://localhost:8080/users/1 -- path variable (care este mandatory)
    // http://localhost:8080/products?name=someValue
    // http://localhost:8080/products?name=someValue&
    // http://localhost:8080/products


    @GetMapping
    public Page<Product> search(@RequestParam Map<String, String> params){
        return productService.search(params);
    }

    @GetMapping(path = "/sortByPrice")
    public List<Product> searchByPrice(){
        return productService.sortByPrice();
    }

    @DeleteMapping(path = "/{id}")
    public HttpStatus delete(@PathVariable Long id){
        productService.delete(id);
        return HttpStatus.OK;
    }

    @PostMapping(path = "/update")
    public ProductDto update(@RequestBody ProductDto productDto) {

        return productService.update(productDto);


    }

    @GetMapping("/categories/{categoryId}")
    public List<Product> findAllByCategory(@PathVariable("categoryId") Long categoryId){
        Category category = categoryService.findById(categoryId);
        return productService.findAllByCategory(category);
    }

}
