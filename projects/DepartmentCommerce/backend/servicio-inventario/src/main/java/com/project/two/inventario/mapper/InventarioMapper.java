package com.project.two.inventario.mapper;


import com.project.two.inventario.domain.Inventario;
import com.project.two.inventario.dto.InventarioDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventarioMapper {

    List<InventarioDTO> listToDTO(List<Inventario> list);

    InventarioDTO toDTO(Inventario inventario);

    Inventario toEntity(InventarioDTO dto);
}
