package com.springboot.webflux.apirest.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class Categorie
 */
@Document(collection = "categories")
@Data
public class Category {
}
