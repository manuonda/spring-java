package org.appmockito.ejemplos.repository;

import java.util.List;

import org.appmockito.ejemplos.models.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExamenRepository extends JpaRepository<Examen, Long> {

	List<Examen> findAll();
	Examen findByNombre(String nombre);
}
