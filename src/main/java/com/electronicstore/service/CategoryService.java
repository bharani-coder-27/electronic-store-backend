package com.electronicstore.service;

import com.electronicstore.model.Category;
import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
}
