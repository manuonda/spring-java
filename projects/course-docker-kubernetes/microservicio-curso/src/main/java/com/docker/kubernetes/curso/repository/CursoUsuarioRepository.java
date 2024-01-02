package com.docker.kubernetes.curso.repository;

import com.docker.kubernetes.curso.domain.entity.CursoUsuario;
import feign.Param;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CursoUsuarioRepository extends JpaRepository<CursoUsuario, Long> {

    /**
     * Funcion que permite obtener un curso asignado al usuario
     * @param idCurso
     * @param idUsuario
     * @return
     */

    @Query("SELECT cu FROM com.docker.kubernetes.curso.domain.entity.CursoUsuario cu WHERE cu.curso.id = ?1 AND cu.usuario.id = ?2")
    Optional<CursoUsuario> findByCursoIdAndUsuarioId(Long idCurso, Long idUsuario);

    @Query("SELECT cu FROM tbl_curso_usuario cu WHERE cu.curso.id = ?1")
    List<CursoUsuario> findByIdCurso(@Param("id") Long idCurso);

}
