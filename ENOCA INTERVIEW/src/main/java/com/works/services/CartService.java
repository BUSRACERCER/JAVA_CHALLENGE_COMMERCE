package com.works.services;

import com.works.entities.Cart;
import com.works.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CartService {

private final CartRepository cartRepository;
    public Cart getCartByCustomerId(Long customerId) {
        return cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new NoSuchElementException("Cart not found for customer with id: " + customerId));
    }

    public Cart updateCart(Long customerId, Cart cart) {
        cart.setCid(customerId);
        return cartRepository.save(cart);
    }

    public void emptyCart(Long customerId) {
        Cart cart = getCartByCustomerId(customerId);
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

}
