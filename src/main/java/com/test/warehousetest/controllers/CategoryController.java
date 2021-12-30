package com.test.warehousetest.controllers;

import com.test.warehousetest.dao.CategoryRepository;
import com.test.warehousetest.models.Category;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(value = "/category/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Category getCategory(@PathVariable long id){
        return categoryRepository.findById(id).orElse(null);
    }

    @PostMapping(value = "/category",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public Category addCategory(@RequestBody Category category){
        return categoryRepository.save(category);
    }

    @PutMapping(value = "/category/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Category updateCategory(@PathVariable long id,
                                @RequestBody Category category){
        Category savedCategory = categoryRepository.getById(id);
        savedCategory.setName(category.getName());
        return categoryRepository.save(savedCategory);
    }

    @GetMapping(value = "/categories",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @DeleteMapping(value = "/category/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteItem(@PathVariable("id") long id){
        categoryRepository.deleteById(id);
    }
}
