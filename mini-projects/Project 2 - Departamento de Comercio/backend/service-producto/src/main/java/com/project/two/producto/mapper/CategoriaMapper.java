package com.project.two.producto.mapper;


import com.project.two.producto.domain.Categoria;
import com.project.two.producto.dto.CategoriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    Categoria toEntityCategoria(CategoriaDTO dto);
    CategoriaDTO toDTOCategoria(Categoria categoria);

    List<CategoriaDTO> toListCategoriaDTO(List<Categoria> listado);

    // ignore field id because not update
    @Mapping(target = "id", ignore = true)
    void updateCategoriaFromDTO(CategoriaDTO categoriaDTO, @MappingTarget Categoria categoria);
}
