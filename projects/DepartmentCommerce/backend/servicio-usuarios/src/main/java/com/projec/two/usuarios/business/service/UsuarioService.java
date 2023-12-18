package com.projec.two.usuarios.business.service;

import com.projec.two.usuarios.domain.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO save(UsuarioDTO usuarioDTO) throws Exception;

    UsuarioDTO update(UsuarioDTO usuarioDTO);


}
