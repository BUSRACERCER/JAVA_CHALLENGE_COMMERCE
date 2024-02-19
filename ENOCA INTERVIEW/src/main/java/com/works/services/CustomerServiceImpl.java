package com.works.services;

import com.works.dto.CustomerDto;
import com.works.dto.CustomerService;
import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
private final CustomerRepository customerRepository;
private final ModelMapper modelMapper;
@Override
    public CustomerDto createCustomer(CustomerDto customerDto){
    Customer customer = modelMapper.map(customerDto, Customer.class);
    customer.setCreatedBy("Customer");
    return modelMapper.map(customerRepository.save(customer), CustomerDto.class);

}
@Override
    public List<CustomerDto> getCustomers(){
    List<Customer> customers =customerRepository.findAll();
    List<CustomerDto> dtos = customers.stream().map(customer -> modelMapper.map(customer,CustomerDto.class)).collect(Collectors.toList());

    return dtos;
}

    @Override
    public CustomerDto getCustomer() {
        return null;
    }
}
