package com.docker.kubernetes.usuarios.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc-cursos")
public interface CursoClient {


    @DeleteMapping("/api/v1/eliminar-curso-usuario/{id}")
    public String findById(@PathVariable("id") Long id); // thVariable("id") Long id);
}
