package com.web.metrica.service;

import com.web.metrica.domain.Empleado;
import com.web.metrica.dto.EmpleadoDTO;
import com.web.metrica.exception.EntityNotFoundException;
import com.web.metrica.repository.EmpleadoRepository;

import java.util.List;

public interface EmpleadoService {

    List<EmpleadoDTO> findAll();
    EmpleadoDTO saveEmpleado(EmpleadoDTO dto);

    EmpleadoDTO updateEmplead(EmpleadoDTO empleadoDTO, Long id);

    void deleteEmpleado(Long id) throws EntityNotFoundException;

    EmpleadoDTO findById(Long id) throws EntityNotFoundException;
}
