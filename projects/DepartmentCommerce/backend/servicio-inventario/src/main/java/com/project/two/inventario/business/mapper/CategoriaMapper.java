package com.project.two.inventario.business.mapper;


import com.project.two.inventario.domain.dto.CategoriaDTO;
import com.project.two.inventario.domain.entity.Categoria;
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

    default Categoria fromId( Long id) {
        if (id == null) {
            return null;
        }

        Categoria categoria = new Categoria();
        categoria.setId(id);
        return categoria;
    }
}
