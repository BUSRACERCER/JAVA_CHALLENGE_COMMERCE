package com.works.services;


import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractProductService
{
    @Autowired
    protected ProductRepository productRepository;

    public abstract Product create(Product entity);

    public abstract Product get(Integer id);

    public abstract List<Product> getAll();

    public abstract void deleteById(Integer id);

    public abstract Product update(Product entity);
}
