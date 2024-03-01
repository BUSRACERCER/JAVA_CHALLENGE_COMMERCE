package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "CARTS")
@SequenceGenerator(name = "CARTS_SEQUENCE", sequenceName = "EXAMPLE_CARTS_SEQ", initialValue = 1, allocationSize = 1)
@Entity
public class Cart extends com.works.entities.Entity<String>
{
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CART_ID")
    private Customer customer;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Order.class)
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "CART_ID")
    private List<Order> orderList = new ArrayList<>();

    private String description;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARTS_SEQUENCE")
    @Column(name = "CART_ID")
    @Id
    @Override
    public Integer getId()
    {
        return super.getId();
    }
}
