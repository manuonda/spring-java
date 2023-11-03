package com.project.two.producto.mapper;


import com.project.two.producto.domain.Producto;
import com.project.two.producto.dto.ProductoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoDTO toProductoDTO(Producto producto);
    Producto toEntity(ProductoDTO productoDTO);

    List<ProductoDTO> toListProductoDTO(List<Producto> listado);

}
