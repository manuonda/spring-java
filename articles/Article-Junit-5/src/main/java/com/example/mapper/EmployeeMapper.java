package com.example.mapper;

import com.example.domain.Employee;
import com.example.dto.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO toEmployeeDTO(Employee employee);

    List<EmployeeDTO> toEmployeeListDTO(List<Employee> employees);

    void updateEmployee(@MappingTarget  Employee target, Employee source);
}
