package com.example.eshopapplication.service.impl;

import com.example.eshopapplication.entity.Product;
import com.example.eshopapplication.repository.ProductRepository;
import com.example.eshopapplication.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(String name, String description, Integer quantity, Double price) {
        productRepository.save(new Product(name, description, quantity, price));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
        productRepository.findById(id);
    }
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

}
