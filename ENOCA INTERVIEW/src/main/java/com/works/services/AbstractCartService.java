package com.works.services;


import com.works.entities.Cart;
import com.works.entities.Order;
import com.works.repositories.CartRepository;
import com.works.repositories.CustomerRepository;
import com.works.repositories.OrderRepository;
import com.works.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractCartService
{
    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected CartRepository cartRepository;

    public abstract boolean removeProductFromCart(String customerId, Integer orderId);

    public abstract Cart addProductToCart(String customerId, Order order);

    public abstract List<Order> getAllOrdersForCustomer(String customerId);

    public abstract Order getOrderForCode(Integer orderId);

    public abstract List<Order> placeOrder(String customerId, Order order);

    public abstract boolean emptyCart(String customerId);

    public abstract Cart update(Cart cart);

    public abstract Cart create(Cart cart);

    public abstract Cart get(String customerId);
}
