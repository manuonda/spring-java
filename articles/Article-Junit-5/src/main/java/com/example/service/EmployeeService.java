package com.example.service;

import com.example.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<EmployeeDTO> saveEmployee(EmployeeDTO employee);
    EmployeeDTO findById(Integer id);

    EmployeeDTO findByEmail(String email);

    List<EmployeeDTO> findAll();
}
