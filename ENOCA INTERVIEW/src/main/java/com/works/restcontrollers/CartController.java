package com.works.restcontrollers;

import com.works.entities.Cart;
import com.works.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{customerId}")
    public ResponseEntity<Cart> getCartByCustomerId(@PathVariable Long customerId) {
        Cart cart = cartService.getCartByCustomerId(customerId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{customerId}/update")
    public ResponseEntity<Cart> updateCart(@PathVariable Long customerId, @RequestBody Cart cart) {
        Cart updatedCart = cartService.updateCart(customerId, cart);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{customerId}/empty")
    public ResponseEntity<Void> emptyCart(@PathVariable Long customerId) {
        cartService.emptyCart(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
