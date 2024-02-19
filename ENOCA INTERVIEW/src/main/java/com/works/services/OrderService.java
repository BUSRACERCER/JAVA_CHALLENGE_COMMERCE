package com.works.services;

import com.works.entities.Cart;
import com.works.entities.CartItem;
import com.works.entities.Order;
import com.works.entities.Product;
import com.works.repositories.CartRepository;
import com.works.repositories.OrderRepository;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    public Order placeOrder(Long customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new NoSuchElementException("Cart not found for customer with id: " + customerId));

        // Sipariş oluşturma işlemleri
        Order order = new Order();
        order.setCustomers(cart.getCustomerId());
        order.setOrderItems(cart.getCartItems());

        // Siparişin toplam fiyatını hesaplama ve güncelleme
        double totalPrice = 0.0;
        for (CartItem cartItem : cart.getCartItems()) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();
            totalPrice += product.getPrice() * quantity;

            // Stok miktarını güncelleme
            int newStockQuantity = product.getStockQuantity() - quantity;
            product.setStockQuantity(newStockQuantity);
            productRepository.save(product);
        }
        order.setTotalPrice(totalPrice);

        // Sepeti temizleme
        cart.getCartItems().clear();
        cartRepository.save(cart);

        // Siparişi kaydetme
        return orderRepository.save(order);
    }

    public Order getOrderForOrderCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new NoSuchElementException("Order not found with order code: " + orderCode));
    }

    public List<Order> getAllOrdersForCustomer(Long customerId) {
        return orderRepository.findByCustomer(customerId);
    }
}
