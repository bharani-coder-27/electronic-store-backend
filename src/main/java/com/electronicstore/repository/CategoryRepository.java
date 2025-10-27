package com.electronicstore.repository;

import com.electronicstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Custom query: find category by name
    Category findByName(String name);
}
