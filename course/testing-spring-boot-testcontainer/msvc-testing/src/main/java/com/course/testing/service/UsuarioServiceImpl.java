package com.course.testing.service;


import com.course.testing.domain.Usuario;
import com.course.testing.exception.ResourceNotFoundException;
import com.course.testing.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{


    //@Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public Usuario guardar(Usuario usuario)  {
        Optional<Usuario> usuarioGuardado = this.usuarioRepository.findByEmail(usuario.getEmail());
        if( usuarioGuardado.isPresent()){
            throw new ResourceNotFoundException("Usuario encontrado por el email" + usuario.getEmail());
        }

        return this.usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return this.usuarioRepository.findAll();
    }
}
