package com.formacionbdi.springboot.app.item.models;

import com.spring.app.commons.models.entity.Producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {

   private Producto producto;
   private Integer cantidad;
   
   
   
   public Double getTotal( ) {
	   return producto.getPrecio() * cantidad.doubleValue();
   }
	
}
