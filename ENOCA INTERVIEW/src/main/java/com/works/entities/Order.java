package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;

@Data
@Table(name = "ORDERS")
@SequenceGenerator(name = "ORDERS_SEQUENCE", sequenceName = "EXAMPLE_ORDERS_SEQ", initialValue = 1, allocationSize = 1)
@Entity
public class Order extends com.works.entities.Entity<String>
{
    @Column(name = "PRODUCT_ID")
    private int productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    private Double price;

    private Integer number;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERS_SEQUENCE")
    @Column(name = "ORDER_ID")
    @Id
    @Override
    public Integer getId()
    {
        return super.getId();
    }
}
