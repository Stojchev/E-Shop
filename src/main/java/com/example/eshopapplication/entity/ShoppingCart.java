package com.example.eshopapplication.entity;

import com.example.eshopapplication.entity.Enum.ShoppingCartStatus;
import lombok.Data;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private LocalDateTime dateCreated;
    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Product> productList;

    public ShoppingCart() {
    }
    public ShoppingCart(User user) {
        this.user=user;
        productList=new ArrayList<>();
        status=ShoppingCartStatus.CREATED;
        this.dateCreated= LocalDateTime.now();
    }
}
