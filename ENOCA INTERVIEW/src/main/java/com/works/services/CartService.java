package com.works.services;


import com.works.entities.Cart;
import com.works.entities.Customer;
import com.works.entities.Order;
import com.works.utility.Util;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService extends AbstractCartService
{
    @Override
    public boolean removeProductFromCart(String customerId, Integer orderId)
    {
        try
        {
            // musteri bulundu
            Customer customer = customerRepository
                    .findById(customerId).orElseThrow(() -> new RuntimeException("Entity not found"));

            // o musteriye ait sepet varsa bulundu
            Cart cart = cartRepository.findCartByCustomerId(customerId);

            if(cart != null)
            {
                Order orderFound = orderRepository
                        .findById(orderId).orElseThrow(() -> new RuntimeException("Entity not found"));
                // o musteriye ait sepete siparis eklendi
                cart.getOrderList().remove(orderFound); // siparis, sepetteki listeden silindi
                return true;
            }
            else  // o musteriye ait sepet yoksa
            {
                return false;
            }
        }

        catch (IllegalArgumentException e)
        {
            Util.showGeneralExceptionInfo(e);
            return false;
        }
        catch (NullPointerException e)
        {
            Util.showGeneralExceptionInfo(e);
            return false;
        }
        catch (RuntimeException e)
        {
            Util.showGeneralExceptionInfo(e);
            return false;
        }
    }

    @Override
    public Cart addProductToCart(String customerId, Order order)
    {
        try
        {
            // musteri bulundu
            Customer customer = customerRepository
                    .findById(customerId).orElseThrow(() -> new RuntimeException("Entity not found"));

            // o musteriye ait sepet varsa bulundu
            Cart cart = cartRepository.findCartByCustomerId(customerId);

            if(cart != null)
            {
                // o musteriye ait sepete siparis eklendi
                cart.getOrderList().add(order);
                return cart;
            }
            else  // o musteriye ait sepet yoksa
            {
                return create(new Cart());
            }
        }

        catch (IllegalArgumentException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
        catch (NullPointerException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
        catch (RuntimeException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }

    @Override
    public List<Order> getAllOrdersForCustomer(String customerId)
    {
        try
        {
            Cart cart = cartRepository.findCartByCustomerId(customerId);
            return cart.getOrderList();
        }
        catch (Exception e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }

    @Override
    public Order getOrderForCode(Integer orderId)
    {
        try
        {
            return orderRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Entity not found"));
        }
        catch (IllegalArgumentException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
        catch (NullPointerException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
        catch (RuntimeException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }

    @Override
    public List<Order> placeOrder(String customerId, Order order)
    {
        try
        {
            Cart cart = get(customerId);

            cart.getOrderList().add(order);

            return cart.getOrderList();
        }
        catch (Exception e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }

    @Override
    public boolean emptyCart(String customerId)
    {
        try
        {
            // musteri ID'ye gore sepet bulundu
            Cart cart = cartRepository.findCartByCustomerId(customerId);

            List<Order> emptyList = new ArrayList<>();
            cart.setOrderList(emptyList);

            update(cart);
            return true;
        }
        catch (Exception e)
        {
            Util.showGeneralExceptionInfo(e);
            return false;
        }
    }

    @Override
    public Cart update(Cart cart)
    {
        // musteri ID'ye gore sepet bulundu
        Cart cartFound = get(cart.getCustomer().getId());

        if(cartFound != null)
        {
            cartFound.setDescription(cart.getDescription() != null ? cart.getDescription() : cartFound.getDescription());
            cartFound.setOrderList(cart.getOrderList() != null ? cart.getOrderList() : cartFound.getOrderList());
        }

        return create(cartFound);
    }

    @Override
    public Cart create(Cart cart)
    {
        try
        {
            return cartRepository.save(cart);
        }
        catch (IllegalArgumentException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
        catch (OptimisticLockingFailureException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }

    @Override
    public Cart get(String customerId)
    {
        try
        {
            return cartRepository.findCartByCustomerId(customerId);
        }
        catch (Exception e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }
}
