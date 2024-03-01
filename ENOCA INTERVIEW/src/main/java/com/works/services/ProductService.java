package com.works.services;


import com.works.entities.Product;
import com.works.utility.Util;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends AbstractProductService
{
    @Override
    public void deleteById(Integer id)
    {
        try
        {
            productRepository.deleteById(id);
        }
        catch (IllegalArgumentException e)
        {
            Util.showGeneralExceptionInfo(e);
        }
    }

    @Override
    public Product update(Product product)
    {
        try
        {
            Product productFound = get(product.getId());

            if(productFound != null)
            {
                productFound.setName(product.getName() != null ? product.getName(): productFound.getName());
                productFound.setCategory(product.getCategory() != null ? product.getCategory() : productFound.getCategory());
                productFound.setPrice(product.getPrice() != null ? product.getPrice() : productFound.getPrice());
                productFound.setNumber(product.getNumber() != null ? product.getNumber() : productFound.getNumber());
            }

            return create(productFound);
        }
        catch (IllegalArgumentException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }

    @Override
    public Product create(Product product)
    {
        try
        {
            return productRepository.save(product);
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
    public Product get(Integer productId)
    {
        try
        {
            return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Entity not found"));
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
    public List<Product> getAll()
    {
        try
        {
            List<Product> productList = productRepository.findAll();
            return productList;
        }
        catch (IllegalArgumentException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }
}
