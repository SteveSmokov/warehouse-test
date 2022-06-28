package com.test.warehousetest.services;

import com.test.warehousetest.dao.CategoryRepository;
import com.test.warehousetest.exceptions.ResourceNotFoundException;
import com.test.warehousetest.models.Category;
import com.test.warehousetest.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final String EXCEPTION_MESSAGE = "Category with ID - %o not found";
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategory(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_MESSAGE,id)));
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(long id, Category category) {
        Category savedCategory = categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_MESSAGE,id)));
        savedCategory.setName(category.getName());
        return categoryRepository.save(savedCategory);
    }

    @Override
    public Category editCategory(long id, String name) {
        Category savedCategory = categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_MESSAGE,id)));
        savedCategory.setName(name);
        return categoryRepository.save(savedCategory);
    }

    @Override
    public void deleteCategory(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_MESSAGE,id)));
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
