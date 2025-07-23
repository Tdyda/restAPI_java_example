package com.example.restapi_java.service.ex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private String name;
    private Double price;
    private Integer rating; // od 1 do 5, ale może być null
}

