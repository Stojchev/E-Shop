package com.example.eshopapplication.repository;

import com.example.eshopapplication.entity.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product,Long> {
    void deleteById(@NonNull Long Id);
}
