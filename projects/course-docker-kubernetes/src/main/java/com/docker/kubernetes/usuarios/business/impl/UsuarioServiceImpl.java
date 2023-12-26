package com.docker.kubernetes.usuarios.business.impl;

import com.docker.kubernetes.usuarios.business.mapper.UsuarioMapper;
import com.docker.kubernetes.usuarios.domain.dto.UsuarioDTO;
import com.docker.kubernetes.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl extends UsuarioService{

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDTO findByUsername(String username) {
        return null;
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public List<UsuarioDTO> findAll() {
        return null;
    }

    @Override
    public UsuarioDTO update(UsuarioDTO dto, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
