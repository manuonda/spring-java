package com.docker.kubernetes.curso.business.service;

import com.docker.kubernetes.curso.domain.dto.CursoDTO;

import java.util.List;

public interface CursoService {

     CursoDTO save(CursoDTO curso);

     CursoDTO update(CursoDTO dto, Long id);

     List<CursoDTO> findAll();

     CursoDTO findById(Long id);

     void delete(Long id);

}
