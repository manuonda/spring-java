package com.docker.kubernetes.curso.business.mapper;


import com.docker.kubernetes.curso.domain.dto.CursoDTO;
import com.docker.kubernetes.curso.domain.entity.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper( componentModel = "spring" )
public interface CursoMapper {

    CursoDTO toDTO(Curso curso);

    Curso toEntity(CursoDTO dto);

    List<CursoDTO> toListDTO (List<Curso> list);

    void updateEntityFromDTO(CursoDTO dto, @MappingTarget Curso curso);
}
