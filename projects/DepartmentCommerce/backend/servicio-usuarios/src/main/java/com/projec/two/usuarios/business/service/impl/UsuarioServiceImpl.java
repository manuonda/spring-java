package com.projec.two.usuarios.business.service.impl;

import com.projec.two.usuarios.business.mapper.UsuarioMapper;
import com.projec.two.usuarios.business.service.UsuarioService;
import com.projec.two.usuarios.domain.dto.UsuarioDTO;
import com.projec.two.usuarios.domain.entity.Usuario;
import com.projec.two.usuarios.presentation.advice.EntityFoundException;
import com.projec.two.usuarios.presentation.advice.EntityNotFoundException;
import com.projec.two.usuarios.repository.UsuarioRepository;
import com.projec.two.usuarios.util.Constants;
import com.project.two.commons.dto.RequestEmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
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

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${spring.kafka.topic.envio_email}")
    private String topicEmail;

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

        if ( findEmail.isPresent()) {
            throw new EntityFoundException("El email existe en otro usuarios");
        }




        Usuario usuario = this.usuarioMapper.toEntity(usuarioDTO);
        Usuario usuarioSave = this.usuarioRepository.save(usuario);
        UsuarioDTO usuarioSavedDTO = this.usuarioMapper.toDTO(usuarioSave);
        //Send email alta de usuario
        SendEmailAltaUsuario(usuarioSavedDTO);
        return usuarioSavedDTO;
    }


    /**
     * Envio de email de Alta de Usuario
     * @param usuarioDTO
     */

    // TODO: terminar y probar envio de email al dar
    // de alta usuario
    public void SendEmailAltaUsuario(UsuarioDTO usuarioDTO){

        Context context = new Context();
        context.setVariable("userName",  usuarioDTO.getUserName());
        context.setVariable("lastName", usuarioDTO.getUserName());
        context.setVariable("url_link", "http://localhost:8080/login");

        String html = this.templateEngine.process("alta-usuario.html", context);
        RequestEmailDTO requestEmailDTO = new RequestEmailDTO(
                usuarioDTO.getEmail(),
                Constants.SUBJECT_EMAIL_USUARIO_ALTA,
                html,
                null);

        var complete = kafkaTemplate.send(topicEmail, requestEmailDTO);
        System.out.println(complete);

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

    @Override
    public List<UsuarioDTO> list() {
       List<Usuario> list = this.usuarioRepository.findAll();
       return this.usuarioMapper.toListDTO(list);
    }

    @Override
    public List<UsuarioDTO> paginable(int page, int size) {
        return null;
    }



}
