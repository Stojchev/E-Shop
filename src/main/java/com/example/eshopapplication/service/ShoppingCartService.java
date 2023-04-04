package com.example.eshopapplication.service;

import com.example.eshopapplication.entity.Product;
import com.example.eshopapplication.entity.ShoppingCart;
import com.example.eshopapplication.entity.exception.ProductAlreadyInShoppingCartException;
import com.example.eshopapplication.entity.exception.ProductNotFoundException;

import java.util.List;

public interface ShoppingCartService {
    List<Product> listAllProductsInShoppingCart(Long cartId);

    void addProductToShoppingCart(String username, Long productId) throws ProductAlreadyInShoppingCartException, ProductNotFoundException;
    ShoppingCart getActiveShoppingCart(String username);
    void deleteProductFromShoppingCart(Long id) throws ProductNotFoundException;
}
