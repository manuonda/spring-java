package com.docker.kubernetes.curso.business.service.impl;

import com.docker.kubernetes.curso.business.mapper.CursoMapper;
import com.docker.kubernetes.curso.business.service.CursoService;
import com.docker.kubernetes.curso.domain.dto.CursoDTO;
import com.docker.kubernetes.curso.domain.entity.Curso;
import com.docker.kubernetes.curso.presentation.advice.EntityFound;
import com.docker.kubernetes.curso.presentation.advice.EntityNotFound;
import com.docker.kubernetes.curso.repository.CursoRepository;
import com.docker.kubernetes.curso.repository.CursoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    private final CursoUsuarioRepository cursoUsuarioRepository;

    @Autowired
    private CursoMapper cursoMapper;

    public CursoServiceImpl(CursoRepository cursoRepository, CursoUsuarioRepository cursoUsuarioRepository) {
        this.cursoRepository = cursoRepository;
        this.cursoUsuarioRepository = cursoUsuarioRepository;
    }

    @Override
    public CursoDTO save(CursoDTO cursoDTO) {
       Optional<Curso> findByName = this.cursoRepository.findByName(cursoDTO.getName());
       if ( findByName.isPresent()){
           throw new EntityFound("El nombre del curso existe");
       }
       Curso curso = this.cursoMapper.toEntity(cursoDTO);
       Curso cursoSave =this.cursoRepository.save(curso);
       return this.cursoMapper.toDTO(cursoSave);
    }

    @Override
    public CursoDTO update(CursoDTO dto, Long id) {
        Curso curso = this.cursoRepository.findById(id)
                .orElseThrow(() -> {
                    throw new EntityNotFound("Curso con id no existe");
                });
        this.cursoMapper.updateEntityFromDTO(dto,curso);
        Curso cursoSave  = this.cursoRepository.save(curso);
        return this.cursoMapper.toDTO(cursoSave);
    }

    @Override
    public List<CursoDTO> findAll() {
        List<Curso> list = this.cursoRepository.findAll();
        return this.cursoMapper.toListDTO(list);
    }

    @Override
    public CursoDTO findById(Long id) {
        Curso curso = this.cursoRepository.findById(id)
                .orElseThrow(() -> {
                    throw new EntityNotFound("Curso con id no existe");
                });

        return this.cursoMapper.toDTO(curso);

    }

    @Override
    public void delete(Long id) {

        //TOOD: no puede eliminar si el curso tiene asigando
        //usuarios
    }
}
