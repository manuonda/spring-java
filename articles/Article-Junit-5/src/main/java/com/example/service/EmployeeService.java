package com.example.service;

import com.example.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employee);
    EmployeeDTO findById(Integer id);

    EmployeeDTO findByEmail(String email);

    List<EmployeeDTO> findAll();

    EmployeeDTO deleteById(Integer id);

    EmployeeDTO update(Integer id, EmployeeDTO dto);
}
