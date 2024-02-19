package com.works.restcontrollers;

import com.works.entities.Order;
import com.works.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place-order/{customerId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long customerId) {
        Order order = orderService.placeOrder(customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/{orderCode}")
    public ResponseEntity<Order> getOrderForOrderCode(@PathVariable String orderCode) {
        Order order = orderService.getOrderForOrderCode(orderCode);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getAllOrdersForCustomer(@PathVariable Long customerId) {
        List<Order> orders = orderService.getAllOrdersForCustomer(customerId);
        return ResponseEntity.ok(orders);
    }
}
