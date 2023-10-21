package com.example.controller;


import com.example.dto.EmployeeDTO;
import com.example.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
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
        return ResponseEntity.created(URI.create("/"+ employeeDTO.id())).body(employeeDTO);
    }

    @GetMapping("/findByEmail/{email}")
    @PreAuthorize("anonymous")
    @CrossOrigin
    public ResponseEntity<EmployeeDTO> findByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(this.employeeService.findByEmail(email));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable("id") Integer id,  @RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok(this.employeeService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN'")
    public ResponseEntity<EmployeeDTO> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.employeeService.deleteById(id));
    }


}
