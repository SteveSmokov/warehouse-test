package com.test.warehousetest.services;

import com.test.warehousetest.models.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(long id);
    Category addCategory(Category category);
    Category updateCategory(long id, Category category);
    Category editCategory(long id, String name);
    void deleteCategory(long id);
    List<Category> getCategories();

}
