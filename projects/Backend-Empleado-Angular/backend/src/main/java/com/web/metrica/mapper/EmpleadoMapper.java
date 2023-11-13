package com.web.metrica.mapper;

import com.web.metrica.domain.Empleado;
import com.web.metrica.dto.EmpleadoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    EmpleadoDTO toDTO(Empleado entity);

    Empleado toEntity(EmpleadoDTO dto);

    List<EmpleadoDTO> listToDTO(List<Empleado> list);
}
