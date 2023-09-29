package com.example.service;

import com.example.domain.Employee;
import com.example.dto.EmployeeDTO;
import com.example.exception.EmployeeNotFoundException;
import com.example.mapper.EmployeeMapper;
import com.example.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> foundEmployee = this.employeeRepository.findByEmail(employeeDTO.getEmail());
        if ( !foundEmployee.isPresent()) {
           throw new EmployeeNotFoundException("Not found employe", HttpStatus.NOT_FOUND);
        }
        Employee employee = Employee.builder()
                .id(employeeDTO.getId())
                .nombre(employeeDTO.getNombre())
                .apellido(employeeDTO.getApellido())
                .email(employeeDTO.getEmail())
                .build();
        Employee employeeSave = employeeRepository.save(employee);
        employeeDTO = EmployeeDTO.builder()
                .id(employeeSave.getId())
                .nombre(employeeSave.getNombre())
                .apellido(employeeSave.getApellido())
                .email(employeeSave.getEmail())
                .build();
        return employeeDTO;

    }

    @Override
    public EmployeeDTO findById(Integer id) {
        Optional<Employee> found =this.employeeRepository.findById(id);
        if (!found.isPresent()) {
            throw new EmployeeNotFoundException("Not found Employee", HttpStatus.NOT_FOUND);
        }
        Employee employee = found.get();


//       EmployeeDTO dto =  EmployeeDTO.builder()
//               .id(employee.getId())
//               .nombre(employee.getNombre())
//               .email(employee.getEmail())
//               .apellido(employee.getApellido())
//               .build();
        EmployeeDTO dto = employeeMapper.toEmployeeDTO(employee);
        return dto;
    }

    @Override
    public EmployeeDTO findByEmail(String email) {
        Optional<Employee> optionalEmployee = this.employeeRepository.findByEmail(email);
        if ( !optionalEmployee.isPresent()) {
             throw new EmployeeNotFoundException("User not found by Email : " + email, HttpStatus.NOT_FOUND);
        }
        Employee employee = optionalEmployee.get();
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .id(employee.getId())
                .nombre(employee.getNombre())
                .apellido(employee.getApellido())
                .email(employee.getEmail())
                .build();
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> list = this.employeeRepository.findAll();
        List<EmployeeDTO> listDTO = new ArrayList<>();
        listDTO = this.employeeMapper.toEmployeeListDTO(list);

        /*for(Employee employee: list ) {
            EmployeeDTO dto = EmployeeDTO.builder()
                    .id(employee.getId())
                    .nombre(employee.getNombre())
                    .apellido(employee.getApellido())
                    .email(employee.getEmail())
                    .build();
            listDTO.add(dto);
        }*/
        return listDTO;
    }

    @Override
    public EmployeeDTO deleteById(Integer id) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Founed", HttpStatus.NOT_FOUND));
        this.employeeRepository.deleteById(id);
        return employeeMapper.toEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO update(Integer id, EmployeeDTO dto) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() ->  new EmployeeNotFoundException("Not Found Employee", HttpStatus.NOT_FOUND));
        // target , source
        employeeMapper.updateEmployee(employee, employeeMapper.toEmployee(dto));

        Employee employeeUpdate =  employeeRepository.save(employee);
        return employeeMapper.toEmployeeDTO(employeeUpdate);
    }
}

