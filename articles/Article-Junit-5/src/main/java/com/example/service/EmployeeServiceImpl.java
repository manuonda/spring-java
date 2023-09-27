package com.example.service;

import com.example.domain.Employee;
import com.example.dto.EmployeeDTO;
import com.example.exception.EmployeeNotFoundException;
import com.example.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<EmployeeDTO> saveEmployee(EmployeeDTO employee) {
        Optional<Employee> employee1 = this.employeeRepository.findByEmail(employee.getEmail());
        if ( !employee1.isPresent()) {
           throw new EmployeeNotFoundException("Not found employe", HttpStatus.NOT_FOUND);
        }
        return Optional.empty();
    }

    @Override
    public EmployeeDTO findById(Integer id) {
        Optional<Employee> found =this.employeeRepository.findById(id);
        if (!found.isPresent()) {
            throw new EmployeeNotFoundException("Not found Employee", HttpStatus.NOT_FOUND);
        }
        Employee employee = found.get();


       EmployeeDTO dto =  EmployeeDTO.builder()
               .id(employee.getId())
               .nombre(employee.getNombre())
               .email(employee.getEmail())
               .apellido(employee.getApellido())
               .build();
        return dto;
    }

    @Override
    public EmployeeDTO findByEmail(String email) {
        return null;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> list = this.employeeRepository.findAll();
        List<EmployeeDTO> listDTO = new ArrayList<>();
        for(Employee employee: list ) {
            EmployeeDTO dto = EmployeeDTO.builder()
                    .id(employee.getId())
                    .nombre(employee.getNombre())
                    .apellido(employee.getApellido())
                    .email(employee.getEmail())
                    .build();
            listDTO.add(dto);
        }
        return listDTO;
    }
}

