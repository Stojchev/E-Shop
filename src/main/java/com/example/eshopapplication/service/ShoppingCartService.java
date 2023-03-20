package com.example.eshopapplication.service;

import com.example.eshopapplication.entity.Product;
import com.example.eshopapplication.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<Product> listAllProductsInShoppingCart(Long cartId);

    ShoppingCart addProductToShoppingCart(String username,Long productId);
    ShoppingCart getActiveShoppingCart(String username);
}
