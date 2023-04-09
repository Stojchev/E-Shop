package com.example.eshopapplication.service;

import com.example.eshopapplication.entity.Category;
import com.example.eshopapplication.entity.Product;
import com.example.eshopapplication.entity.exception.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    void save(String name,
              String description,
              Integer quantity,
              Double price,
              String photo,
              List<Category> categories);
    void edit(Long id,
              String name,
              String description,
              Integer quantity,
              Double price,
              String photo,
              List<Category> categories) throws ProductNotFoundException;
    void deleteById(Long id);
//    void setPhoto(String photo);
    Optional<Product> findById(Long id);
}
