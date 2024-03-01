package com.works.restcontrollers;

import com.works.entities.Cart;
import com.works.entities.Customer;
import com.works.entities.Order;
import com.works.entities.Product;
import com.works.services.AbstractCartService;
import com.works.services.AbstractCustomerService;
import com.works.services.AbstractProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("api/order-management")
@RestController
public class OrderManagementController
{
    @Autowired
    private AbstractCartService cartService;

    @Autowired
    private AbstractCustomerService customerService;

    @Autowired
    private AbstractProductService productService;


    @DeleteMapping("cart/delete/{id}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable(name = "id") String customerId, @RequestBody Order order)
    {
        if (cartService.removeProductFromCart(customerId, order.getId()))
        {
            return ResponseEntity.ok("Product have productID:" + order.getProductId()
                    + " has been updated.");
        }
        else
        {
            return new ResponseEntity<>("Product have productID:" + order.getProductId()
                    + " can not be deleted.", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("cart/add/{id}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable(name = "id") String customerId, @RequestBody Order order)
    {
        Cart savedCart = cartService.addProductToCart(customerId, order);

        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }

    @PostMapping("order/{id}")
    public ResponseEntity<Order> getOrderForCode(@PathVariable(name = "id") Integer orderId)
    {
        Order order = cartService.getOrderForCode(orderId);

        return ResponseEntity.ok( order );
    }

    @PostMapping("cart/place/{id}")
    public ResponseEntity<List<Order>> placeOrder(@PathVariable(name = "id") String customerId, @RequestBody Order order)
    {
        return ResponseEntity.ok( cartService.placeOrder(customerId, order) );
    }

    @PostMapping("cart/empty/{id}")
    public ResponseEntity<String> emptyCart(@PathVariable(name = "id") String customerId)
    {
        if (cartService.emptyCart(customerId))
        {
            return ResponseEntity.ok("Empty cart operation is successful.");
        }
        else
        {
            return new ResponseEntity<>("Empty cart operation is unsuccessful.", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("cart/show-orders/{id}")
    public ResponseEntity<List<Order>> showOrders(@PathVariable(name = "id") String customerId)
    {
        return ResponseEntity.ok(cartService.getAllOrdersForCustomer(customerId));
    }

    @GetMapping("cart/update/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable(name = "id") String customerId, Cart cart)
    {
        Cart cartFound = cartService.get(customerId);

        return ResponseEntity.ok(cartService.update(cartFound));
    }

    @GetMapping("cart/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable(name = "id") String customerId)
    {
        Cart cart = cartService.get(customerId);

        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("product/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable(name = "id") Integer productID)
    {
        productService.deleteById(productID);

        return ResponseEntity.ok("Product have productID:" + productID + " has been deleted.");
    }

    @PostMapping("product/update")
    public ResponseEntity updateProduct(@RequestBody Product product)
    {
        productService.update(product);

        return ResponseEntity.ok("Product have productID:" + product.getId() + " has been updated.");
    }

    @PostMapping("product/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product)
    {
        product.setCreated(new Date());
        Product savedProduct = productService.create(product);

        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("product")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        List<Product> productList = productService.getAll();

        return ResponseEntity.ok(productList);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "id") Integer productID)
    {
        Product product = productService.get(productID);

        return ResponseEntity.ok(product);
    }

    @PostMapping("customer/add")
    public ResponseEntity addCustomer(@RequestBody Customer customer)
    {
        Object customerAdded = customerService.add(customer);


        if(customerAdded instanceof Customer)
            return new ResponseEntity<>(customerAdded, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(customerAdded, HttpStatus.NOT_ACCEPTABLE);
    }
}
