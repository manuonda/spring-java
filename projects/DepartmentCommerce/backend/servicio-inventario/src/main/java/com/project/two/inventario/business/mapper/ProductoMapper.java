package com.project.two.inventario.business.mapper;


import com.project.two.commons.dto.ProductoDTO;
import com.project.two.inventario.domain.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {CategoriaMapper.class})
public interface ProductoMapper {



    @Mapping(source = "categoria.id", target="idCategoria")
    ProductoDTO toProductoDTO(Producto producto);

    @Mapping(source = "idCategoria", target = "categoria")
    Producto toEntity(ProductoDTO productoDTO);

    List<ProductoDTO> toListProductoDTO(List<Producto> listado);

    default Producto fromId(Long idProducto) {
        if(idProducto == null) {
            return null;
        }
        Producto producto = new Producto();
        producto.setId(idProducto);
        return producto;

    }



}
