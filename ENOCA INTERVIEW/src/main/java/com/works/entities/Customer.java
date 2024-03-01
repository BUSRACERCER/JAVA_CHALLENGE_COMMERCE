package com.works.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "CUSTOMERS")
@Entity
public class Customer extends com.works.entities.Entity<String>
{
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    private String email;

    private String phone;


    @Column(name = "CUSTOMER_ID")
    @Id
    @Override
    public String getId()
    {
        return super.getId();
    }
}
