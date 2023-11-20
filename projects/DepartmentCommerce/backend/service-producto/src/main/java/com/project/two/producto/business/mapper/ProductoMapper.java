package com.project.two.producto.business.mapper;


import com.project.two.producto.domain.entity.Producto;
import com.project.two.producto.domain.dto.ProductoDTO;
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
