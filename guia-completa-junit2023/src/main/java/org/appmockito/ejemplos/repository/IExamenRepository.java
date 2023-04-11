package org.appmockito.ejemplos.repository;

import java.util.List;
import java.util.Optional;

import org.appmockito.ejemplos.models.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IExamenRepository extends JpaRepository<Examen, Long> {

	List<Examen> findAll();
	Optional<Examen> findByNombre(String nombre);
	Examen guardar(Examen examen);
}
