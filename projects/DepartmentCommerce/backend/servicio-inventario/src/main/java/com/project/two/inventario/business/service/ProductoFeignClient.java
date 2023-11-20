package com.project.two.inventario.business.service;


import com.project.two.commons.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service",url = "http://localhost:9090/api/v1/productos")
public interface ProductoFeignClient {
    @GetMapping("/{id}")
    ProductoDTO findById(@PathVariable("id") Long idProducto);
}
