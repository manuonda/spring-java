package com.docker.kubernetes.usuarios.business.impl;

import com.docker.kubernetes.usuarios.domain.dto.UsuarioDTO;
import com.docker.kubernetes.usuarios.domain.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    UsuarioDTO findByUsername(String username);

    UsuarioDTO save(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> findAll();

    UsuarioDTO update(UsuarioDTO dto, Long id);

    void delete(Long id);

    UsuarioDTO findById(Long id);

    /**
     * Obtengo un listado de usuarios
     * por los ids
     * @param ids
     * @return
     */
    List<UsuarioDTO> findByIds(List<Long> ids);

}
