package com.example.eshopapplication.service.impl;

import com.example.eshopapplication.entity.Enum.ShoppingCartStatus;
import com.example.eshopapplication.entity.Product;
import com.example.eshopapplication.entity.ShoppingCart;
import com.example.eshopapplication.entity.User;
import com.example.eshopapplication.entity.exception.ShoppingCartNotFoundException;
import com.example.eshopapplication.repository.ProductRepository;
import com.example.eshopapplication.repository.ShoppingCartRepository;
import com.example.eshopapplication.repository.UserRepository;
import com.example.eshopapplication.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(this.shoppingCartRepository.findById(cartId).isEmpty()){
            throw new ShoppingCartNotFoundException(cartId);
        }
        return shoppingCartRepository.findById(cartId).get().getProductList();
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
//        User user= userRepository.findBy
//        if(this.shoppingCartRepository.findByUserAndStatus(username, ShoppingCartStatus.CREATED).isEmpty()){
//            throw new ShoppingCartNotFoundException(cartId);
//        }
        return null;
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return null;
    }
}
