package com.works.services;


import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCustomerService
{
    @Autowired
    protected CustomerRepository customerRepository;

    public abstract Object add(Customer customer);
}
