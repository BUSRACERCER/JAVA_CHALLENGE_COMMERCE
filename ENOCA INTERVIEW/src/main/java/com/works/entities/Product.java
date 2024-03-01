package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "PRODUCTS")
@SequenceGenerator(name = "PRODUCTS_SEQUENCE", sequenceName = "EXAMPLE_PRODUCTS_SEQ", initialValue = 1, allocationSize = 1)
@Entity
public class Product extends com.works.entities.Entity<String>
    private String name;

    private String category;

    private String description;

    private Double price;

    private Integer number;

    private Date created;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTS_SEQUENCE")
    @Column(name = "PRODUCT_ID")
    @Id
    @Override
    public Integer getId()
    {
        return super.getId();
    }
}
