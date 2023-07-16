package com.springboot.webflux.apirest.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "productos")
@Data
public class Product {

    @Id
    private String id;

    String name;
    Date createAt;
    Double price;



}
