package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Data
public class Product extends  Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @NotEmpty
    @NotNull
    private String title;


    private  double price;

    @NotNull
    private  Integer stockQuantity;





}
