package com.docker.kubernetes.curso.business.service;

import com.docker.kubernetes.curso.domain.dto.CursoUsuarioDTO;
import com.docker.kubernetes.curso.domain.dto.UsuarioDTO;
import com.docker.kubernetes.curso.domain.entity.CursoUsuario;

import java.util.List;

public interface CursoUsuarioService {


    List<UsuarioDTO> findUsuariosByCursoId(Long id);

    CursoUsuarioDTO asignarUsuarioToCurso(CursoUsuarioDTO dto);

    List<CursoUsuarioDTO> findAll();

}
