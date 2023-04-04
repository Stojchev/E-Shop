package com.example.eshopapplication.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String description;
    private Integer quantity;
    private double price;
    @Column(length = 64)
    private String photo;
    @OneToMany
    private List<Category> categories=new ArrayList<>();

    public List<Category> getCategories() {
        return categories;
    }

    public Product(String name,
                   String description,
                   Integer quantity,
                   double price,
                   String photo,
                   List<Category> category) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.photo = photo;
        this.categories=category;
    }

    public Product(String name, String description, Integer quantity, double price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String name, String description, Integer quantity, double price, String photo) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.photo = photo;
    }

    public Product() {

    }
//    public boolean checkIfCategory(String cat){
//        return categories.contains()
//    }
}
