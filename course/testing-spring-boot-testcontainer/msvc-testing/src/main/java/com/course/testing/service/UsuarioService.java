package com.course.testing.service;

import com.course.testing.domain.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario guardar(Usuario usuario);

    List<Usuario> getAllUsuarios();

}
