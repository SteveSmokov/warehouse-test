package com.test.warehousetest.controllers;

import com.test.warehousetest.models.Product;
import com.test.warehousetest.dao.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class ProductController {
    @Autowired
    private final ProductRepository repository;

    @GetMapping(value = "/product/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProduct(@PathVariable long id){
        return repository.findById(id).orElseThrow();
    }

    @PostMapping(value = "/product",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Product addProduct(@RequestBody Product product){
        return repository.save(product);
    }

    @PutMapping(value = "/product/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Product updateCategory(@PathVariable long id,
                                @RequestBody Product product){
        Product saved = repository.getById(id);
        saved.setName(product.getName());
        saved.setPrice(product.getPrice());
        saved.setSku(product.getSku());
        saved.setCategory(product.getCategory());
        return repository.save(saved);
    }

    @GetMapping(value = "/products",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllCategories(){
        return repository.findAll();
    }
}
