package com.example.eshopapplication.repository;

import com.example.eshopapplication.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByName(String name);
    void deleteByName(String name);
}
