package com.web.metrica.controller;

import com.web.metrica.dto.EmpleadoDTO;
import com.web.metrica.exception.EntityNotFoundException;
import com.web.metrica.service.EmpleadoService;
import org.apache.coyote.Response;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/empleado")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<EmpleadoDTO>> getAll(){
        return ResponseEntity.ok(this.empleadoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> getById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(this.empleadoService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<EmpleadoDTO> save(@RequestBody EmpleadoDTO dto)  {
        return ResponseEntity.ok(this.empleadoService.saveEmpleado(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws EntityNotFoundException {
        this.empleadoService.deleteEmpleado(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> update(@PathVariable Long id, @RequestBody EmpleadoDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.empleadoService.updateEmplead(dto, id));
    }


}
