package com.projec.two.usuarios.service;

import com.projec.two.usuarios.BaseRepositoryTest;
import com.projec.two.usuarios.business.mapper.UsuarioMapperImpl;
import com.projec.two.usuarios.business.service.impl.UsuarioServiceImpl;
import com.projec.two.usuarios.domain.dto.UsuarioDTO;
import com.projec.two.usuarios.domain.entity.Usuario;
import com.projec.two.usuarios.repository.UsuarioRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UsuariosServiceTest extends BaseRepositoryTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Spy
    private UsuarioMapperImpl usuarioMapper;



    @Test
    public void shouldSaveUsuario_whenSaveUsuario_returnObject() throws Exception {
        //given
        Usuario usuario = Usuario.builder().build();
        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .userName("dgarcia")
                .lastName("garcia")
                .email("manuonda@gmail.com")
                .build();
        //when
        when(this.usuarioRepository.save(usuario)).thenReturn(usuario);
        UsuarioDTO usuarioSaveDTO = this.usuarioService.save(usuarioDTO);

        //then

    }



}
