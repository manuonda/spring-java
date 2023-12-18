package com.projec.two.usuarios.business.service.impl;

import com.projec.two.usuarios.business.mapper.UsuarioMapper;
import com.projec.two.usuarios.business.service.UsuarioService;
import com.projec.two.usuarios.domain.dto.UsuarioDTO;
import com.projec.two.usuarios.domain.entity.Usuario;
import com.projec.two.usuarios.presentation.advice.EntityFoundException;
import com.projec.two.usuarios.presentation.advice.EntityNotFoundException;
import com.projec.two.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Class Service corresponediente a Usuario
 * @author dgarcia
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;


    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) throws Exception {

        Optional<Usuario> findUsername = this.usuarioRepository.findByUserName(usuarioDTO.getUserName());
        Optional<Usuario> findEmail = this.usuarioRepository.findByEmail(usuarioDTO.getEmail());

        if (findUsername.isPresent() ) {
            throw new EntityFoundException("El usuario ya existe");
        }

        if ( findUsername.isPresent()) {
            throw new EntityFoundException("El email existe en otro usuarios");
        }


        Usuario usuario = this.usuarioMapper.toEntity(usuarioDTO);
        Usuario usuarioSave = this.usuarioRepository.save(usuario);
        UsuarioDTO usuarioSavedDTO = this.usuarioMapper.toDTO(usuarioSave);
        return usuarioSavedDTO;
    }

    @Override
    public UsuarioDTO update(UsuarioDTO usuarioDTO) {
        Usuario usuario = this.usuarioRepository.findById(usuarioDTO.getId())
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Usuario no se encuentra");
                });

        Optional<Usuario> findNameUsuario = this.usuarioRepository.findByUserName(usuarioDTO.getUserName());

        if (findNameUsuario.isPresent() &&
           !findNameUsuario.get().getId().equals(usuarioDTO.getId())
        ) {
          throw new EntityFoundException("El UserName ya existe en otro Usuario");
        }

        Usuario usuarioSave = this.usuarioRepository.save(this.usuarioMapper.toEntity(usuarioDTO));
        return this.usuarioMapper.toDTO(usuarioSave);
    }
}
