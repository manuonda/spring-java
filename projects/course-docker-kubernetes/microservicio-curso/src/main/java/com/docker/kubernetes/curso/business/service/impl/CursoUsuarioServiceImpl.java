package com.docker.kubernetes.curso.business.service.impl;


import com.docker.kubernetes.curso.business.mapper.CursoUsuarioMapper;
import com.docker.kubernetes.curso.business.service.CursoUsuarioService;
import com.docker.kubernetes.curso.clients.UsuarioClient;
import com.docker.kubernetes.curso.domain.dto.CursoUsuarioDTO;
import com.docker.kubernetes.curso.domain.dto.UsuarioDTO;
import com.docker.kubernetes.curso.domain.entity.Curso;
import com.docker.kubernetes.curso.domain.entity.CursoUsuario;
import com.docker.kubernetes.curso.domain.models.Usuario;
import com.docker.kubernetes.curso.presentation.advice.EntityFound;
import com.docker.kubernetes.curso.presentation.advice.EntityNotFound;
import com.docker.kubernetes.curso.repository.CursoRepository;
import com.docker.kubernetes.curso.repository.CursoUsuarioRepository;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CursoUsuarioServiceImpl implements CursoUsuarioService {

    private final CursoUsuarioRepository cursoUsuarioRepository;

    private final CursoRepository cursoRepository;

    @Autowired
    private CursoUsuarioMapper cursousuarioMapper;

    private static final Logger logger = LoggerFactory.getLogger(CursoUsuarioServiceImpl.class);

    @Autowired
    private UsuarioClient usuarioClient;


    public CursoUsuarioServiceImpl(CursoUsuarioRepository cursoUsuarioRepository, CursoRepository cursoRepository){
        this.cursoUsuarioRepository = cursoUsuarioRepository;
        this.cursoRepository = cursoRepository;
    }



    @Override
    public List<UsuarioDTO> findUsuariosByCursoId(Long idCurso) {
        logger.info("Find Usuarios by Curso id {}", idCurso);
        List<UsuarioDTO> listUsuarios = new ArrayList<>();
        Curso curso = this.cursoRepository.findById(idCurso)
                .orElseThrow(() -> {
                        throw new EntityNotFound("Curso no existe with id : " + idCurso);
                });
        List<CursoUsuario> listCursoUsuario = this.cursoUsuarioRepository.findByIdCurso(idCurso);
        if ( !listCursoUsuario.isEmpty()){
           List<Long> ids = listCursoUsuario.stream().map(entity -> entity.getUsuario().getId()).toList();

        }
        return null;
    }

    @Override
    public CursoUsuarioDTO asignarUsuarioToCurso(CursoUsuarioDTO dto) {
        Curso curso = this.cursoRepository.findById(dto.getIdCurso())
                .orElseThrow(() -> {
                    throw new EntityNotFound("Curso  no existe with id : " + dto.getIdCurso());
                });


        UsuarioDTO usuarioDTO = null ;
        try {
            usuarioDTO = this.usuarioClient.findById(dto.getIdUsuario());
        }catch (FeignException.FeignClientException ex){
            //TODO : logger error
            System.out.println(ex.getMessage());
            System.out.println("message" + ex.getMessage());
        }

        if (usuarioDTO == null){
            throw new EntityNotFound("Usuario no existe with id : " + dto.getIdUsuario());
        }


        this.cursoUsuarioRepository
                .findByCursoIdAndUsuarioId(dto.getIdCurso(), dto.getIdUsuario())
                .ifPresent( entity ->{
                    throw new EntityFound("El usuario ya pertenece al curso");
                });

        CursoUsuario cursoUsuario = new CursoUsuario();
        Usuario usuario  = new Usuario();
        usuario.setId(dto.getIdUsuario());
        cursoUsuario.setCurso(curso);
        cursoUsuario.setUsuario(usuario);
        CursoUsuario cursoUsuarioSave   = this.cursoUsuarioRepository.save(cursoUsuario);
        return null;
    }

    @Override
    public List<CursoUsuarioDTO> findAll() {
        List<CursoUsuario> list = this.cursoUsuarioRepository.findAll();
        return list.stream()
                .map(this.cursousuarioMapper::toDTO)
                .collect(Collectors.toList());
    }


}
