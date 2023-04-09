package com.example.eshopapplication.entity;

import lombok.Data;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @Column(length = 256)
    private String photo;
    @ManyToMany
    private List<Category> categories=new ArrayList<>();

//    public void addImages(List<MultipartFile> files) throws IOException {
//        for (MultipartFile file : files) {
//            String fileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
//            Path path = Paths.get("/path/to/image/directory", fileName);
//            Files.write(path, file.getBytes());
//            imageUrls.add(fileName);
//        }
//    }
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
