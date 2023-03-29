package com.example.eshopapplication.repository;

import com.example.eshopapplication.entity.Enum.ShoppingCartStatus;
import com.example.eshopapplication.entity.ShoppingCart;
import com.example.eshopapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
