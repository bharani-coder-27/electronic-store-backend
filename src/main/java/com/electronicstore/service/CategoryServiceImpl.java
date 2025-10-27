package com.electronicstore.service;

import com.electronicstore.model.Category;
import com.electronicstore.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public Category addCategory(Category category) {
        return repo.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existing.setName(category.getName());
        existing.setDescription(category.getDescription());
        return repo.save(existing);
    }

    @Override
    public void deleteCategory(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Category getCategoryById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return repo.findAll();
    }
}
