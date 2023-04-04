package com.example.eshopapplication.repository;

import com.example.eshopapplication.entity.Enum.ShoppingCartStatus;
import com.example.eshopapplication.entity.ShoppingCart;
import com.example.eshopapplication.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
    @NonNull
    List<ShoppingCart> findAll();
}
