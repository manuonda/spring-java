package com.project.two.inventario.business.mapper;


import com.project.two.inventario.domain.entity.Inventario;
import com.project.two.inventario.domain.dto.InventarioDTO;
import com.project.two.inventario.domain.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {ProductoMapper.class})
public interface InventarioMapper {

    List<InventarioDTO> listToDTO(List<Inventario> list);

    InventarioDTO toDTO(Inventario inventario);

    @Mapping(source = "idProducto" , target = "producto")
    Inventario toEntity(InventarioDTO dto);


}
