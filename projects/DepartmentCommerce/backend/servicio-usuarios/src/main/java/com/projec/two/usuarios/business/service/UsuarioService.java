package com.projec.two.usuarios.business.service;

import com.projec.two.usuarios.domain.dto.UsuarioDTO;

public interface UsuarioService {

    UsuarioDTO save(UsuarioDTO usuarioDTO);

    UsuarioDTO update(UsuarioDTO usuarioDTO);

    

}
