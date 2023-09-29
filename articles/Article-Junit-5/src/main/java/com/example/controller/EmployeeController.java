package com.example.controller;


import com.example.dto.EmployeeDTO;
import com.example.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public ResponseEntity<List<EmployeeDTO>> findAll() {
     return ResponseEntity.ok(this.employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.employeeService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> create(@RequestBody EmployeeDTO employee ) {
        EmployeeDTO employeeDTO = this.employeeService.saveEmployee(employee);
        return ResponseEntity.created(URI.create("/"+ employeeDTO.getId())).body(employeeDTO);
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<EmployeeDTO> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(this.employeeService.findByEmail(email));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable("id") Integer id,  @RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok(this.employeeService.update(id, dto));
    }
}
