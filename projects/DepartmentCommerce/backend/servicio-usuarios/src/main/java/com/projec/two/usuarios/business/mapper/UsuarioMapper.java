package com.projec.two.usuarios.business.mapper;


import com.projec.two.usuarios.domain.dto.UsuarioDTO;
import com.projec.two.usuarios.domain.entity.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario usuario);

    Usuario toEntity(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> toListDTO(List<Usuario> listado);

    default Usuario fromId( Long id) {
        if(id == null){
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
}
