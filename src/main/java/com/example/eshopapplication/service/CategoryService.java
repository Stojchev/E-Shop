package com.example.eshopapplication.service;

import com.example.eshopapplication.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category create(String name,String description);
    Category update(String name,String description);
    void delete(String name);
    List<Category> listCategories();
    List<Category> searchCategories(String searchText);
    Optional<Category> findByName(String name);
}
