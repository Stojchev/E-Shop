package com.example.eshopapplication.service.impl;

import com.example.eshopapplication.entity.Category;
import com.example.eshopapplication.entity.Product;
import com.example.eshopapplication.entity.exception.ProductNotFoundException;
import com.example.eshopapplication.repository.ProductRepository;
import com.example.eshopapplication.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void save(String name, String description, Integer quantity, Double price, String photo, List<Category> categories) {
        productRepository.save(new Product(name, description, quantity, price,photo,categories));
    }

    @Override
    @Transactional
    public void edit(Long id,String name, String description, Integer quantity, Double price, String photo, List<Category> categories) throws ProductNotFoundException {
        Product product=this.productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
        product.setName(name);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setPhoto(photo);
        product.setCategories(categories);
    }

//    @Override
//    public void save(String name, String description, Integer quantity, Double price,String photo) {
//        productRepository.save(new Product(name, description, quantity, price,photo));
//    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
        productRepository.findById(id);
    }

//    @Override
//    public void setPhoto(String photo) {
//        productRepository.
//    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

}
