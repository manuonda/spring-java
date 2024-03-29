package com.docker.kubernetes.usuarios.business.impl;

import com.docker.kubernetes.usuarios.business.mapper.UsuarioMapper;
import com.docker.kubernetes.usuarios.domain.dto.UsuarioDTO;
import com.docker.kubernetes.usuarios.domain.entity.Usuario;
import com.docker.kubernetes.usuarios.presentation.advice.EntityFound;
import com.docker.kubernetes.usuarios.presentation.advice.EntityNotFound;
import com.docker.kubernetes.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDTO findByUsername(String username) {
        Usuario usuario = this.usuarioRepository.findByUserName(username)
                .orElseThrow(() -> new EntityNotFound("Usuario no existe"));
        return this.usuarioMapper.toDTO(usuario);

    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Optional<Usuario> findByUsername = this.usuarioRepository
                .findByUserName(usuarioDTO.getUserName());

        Optional<Usuario> findByEmail = this.usuarioRepository.findByEmail(usuarioDTO.getEmail());
        if ( findByUsername.isPresent()){
            throw new EntityFound("Username existe");
        }

        if (findByEmail.isPresent()){
            throw new EntityFound("Email existe");
        }

        Usuario usuario = this.usuarioMapper.toEntity(usuarioDTO);
        Usuario usuarioSave = this.usuarioRepository.save(usuario);

        return this.usuarioMapper.toDTO(usuarioSave);
    }

    @Override
    public List<UsuarioDTO> findAll() {
        return this.usuarioMapper.toListDTO(this.usuarioRepository.findAll());
    }

    @Override
    public UsuarioDTO update(UsuarioDTO dto, Long id) {
        Usuario usuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> {
                     throw new EntityNotFound("Usuario by Id no existe");
                });

        Optional<Usuario> findByUsernName  = this.usuarioRepository
                .findByUserName(dto.getUserName());

        return null;
    }

    @Override
    public void delete(Long id) {
       Usuario usuario = this.usuarioRepository.findById(id)
               .orElseThrow(() -> new EntityNotFound("Usuario no existe"));

       this.usuarioRepository.delete(usuario);
    }

    @Override
    public UsuarioDTO findById(Long id) {
        Usuario usuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Usuario no existe"));
        return this.usuarioMapper.toDTO(usuario);
    }

    @Override
    public List<UsuarioDTO> findByIds(List<Long> ids) {
        List<Usuario> listUsuario = this.usuarioRepository.findByIds(ids);
        return listUsuario.stream()
                .map(this.usuarioMapper::toDTO)
                .toList();
    }
}
