package com.docker.kubernetes.usuarios.repository;

import com.docker.kubernetes.usuarios.domain.entity.Usuario;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUserName(String username);

    @Query(value = "SELECT u FROM tbl_usuarios u WHERE u.id IN (:ids)" ,
    nativeQuery = true)
    List<Usuario> findByIds(@Param("ids") List<Long> ids);
}
