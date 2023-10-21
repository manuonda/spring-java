package com.web.metrica.service;

import com.web.metrica.domain.Empleado;
import com.web.metrica.dto.EmpleadoDTO;
import com.web.metrica.exception.EntityAlreadyExistException;
import com.web.metrica.exception.EntityNotFoundException;
import com.web.metrica.mapper.EmpleadoMapper;
import com.web.metrica.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoMapper empleadoMapper;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }


    @Override
    public List<EmpleadoDTO> findAll() {
        List<Empleado> listado = this.empleadoRepository.findAll();
        return this.empleadoMapper.listToDTO(listado);
    }

    @Override
    public EmpleadoDTO saveEmpleado(EmpleadoDTO dto) throws EntityAlreadyExistException {
        Optional<Empleado> encontrado = this.empleadoRepository.findByEmail(dto.getEmail());
        System.out.println("encontrado : "+ encontrado.isPresent());
        if (encontrado.isPresent() ) {
          throw new EntityAlreadyExistException("Email Empleado ya se encuentra en db");
        }
        Empleado empleado = this.empleadoMapper.toEntity(dto);
        empleado = this.empleadoRepository.save(empleado);
        EmpleadoDTO empleadoDTO = this.empleadoMapper.toDTO(empleado);
        return empleadoDTO;
    }

    @Override
    public EmpleadoDTO updateEmplead(EmpleadoDTO empleadoDTO, Long id) {
        return null;
    }

    @Override
    public void deleteEmpleado(Long id) throws EntityNotFoundException {
      Optional<Empleado> encontrado = this.empleadoRepository.findById(id);
      if(encontrado.isEmpty()) {
         throw new EntityNotFoundException("Empleado no existe por id : ".concat(id.toString()));
      }
      this.empleadoRepository.delete(encontrado.get());
    }

    @Override
    public EmpleadoDTO findById(Long id) throws EntityNotFoundException {
        Optional<Empleado> findEmpleado = this.empleadoRepository.findById(id);
        if( findEmpleado.isEmpty()) {
            throw new EntityNotFoundException("Empleado no se encuentra en base de datos ");
        }
        return this.empleadoMapper.toDTO(findEmpleado.get());
    }
}
