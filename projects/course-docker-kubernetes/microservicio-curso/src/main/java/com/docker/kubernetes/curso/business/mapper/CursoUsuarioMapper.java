package com.docker.kubernetes.curso.business.mapper;


import com.docker.kubernetes.curso.domain.dto.CursoUsuarioDTO;
import com.docker.kubernetes.curso.domain.entity.CursoUsuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring" ,  imports = {})
public interface CursoUsuarioMapper {



     @Mappings({
         @Mapping(source="curso.id", target="idCurso"),
             @Mapping(source="usuario.id", target="idUsuario"),
             @Mapping(source="usuario.userName", target="nombreUsuario"),
             @Mapping(source="curso.name",  target = "nombreCurso")
     })
     CursoUsuarioDTO toDTO(CursoUsuario cursoUsuario);

     CursoUsuario toEntity(CursoUsuarioDTO cursoUsuarioDTO);

     List<CursoUsuarioDTO> toListDTO(List<CursoUsuario> cursoUsuarios);

}
