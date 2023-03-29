package com.example.eshopapplication.service.impl;

import com.example.eshopapplication.entity.Enum.ShoppingCartStatus;
import com.example.eshopapplication.entity.Product;
import com.example.eshopapplication.entity.ShoppingCart;
import com.example.eshopapplication.entity.User;
import com.example.eshopapplication.entity.exception.ProductAlreadyInShoppingCartException;
import com.example.eshopapplication.entity.exception.ProductNotFoundException;
import com.example.eshopapplication.entity.exception.ShoppingCartNotFoundException;
import com.example.eshopapplication.repository.ProductRepository;
import com.example.eshopapplication.repository.ShoppingCartRepository;
import com.example.eshopapplication.repository.UserRepository;
import com.example.eshopapplication.service.ProductService;
import com.example.eshopapplication.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   ProductRepository productRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }


    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(this.shoppingCartRepository.findById(cartId).isEmpty()){
            throw new ShoppingCartNotFoundException(cartId);
        }
        return shoppingCartRepository.findById(cartId).get().getProductList();
    }

    @Override
    public void addProductToShoppingCart(String username, Long productId) throws ProductAlreadyInShoppingCartException, ProductNotFoundException {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = this.productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        if(shoppingCart.getProductList()
                .stream().filter(i -> i.getId().equals(productId))
                .toList().size() > 0)
            throw new ProductAlreadyInShoppingCartException(productId, username);
        shoppingCart.getProductList().add(product);
        this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String email) {
        User user = this.userRepository.findByEmail(email);
        return this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }
}
