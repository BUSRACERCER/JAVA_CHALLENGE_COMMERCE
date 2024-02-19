package com.works.dto;

import java.util.List;

public interface CustomerService {
 CustomerDto createCustomer(CustomerDto customer);
 List<CustomerDto> getCustomers();
 CustomerDto getCustomer();


}
