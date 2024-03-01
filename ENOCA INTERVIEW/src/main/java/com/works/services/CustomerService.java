package com.works.services;


import com.works.entities.Customer;
import com.works.utility.Util;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends AbstractCustomerService
{
    @Override
    public Object add(Customer customer)
    {
        try
        {
            if(Util.isValidTCKN(customer.getId()))
            {
                return customerRepository.save(customer);
            }
            else
            {
                return new String("TCKN is not valid.");
            }
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
}
