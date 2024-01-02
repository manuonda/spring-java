package com.docker.kubernetes.curso.clients;


import com.docker.kubernetes.curso.domain.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-usuario", url="${service.usuarios}")
public interface UsuarioClient {


    @GetMapping("/api/v1/usuarios/{id}")
    public UsuarioDTO findById(@PathVariable("id") Long id);

}
