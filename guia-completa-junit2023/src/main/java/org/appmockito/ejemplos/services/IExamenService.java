package org.appmockito.ejemplos.services;

import java.util.List;

import org.appmockito.ejemplos.models.Examen;

public interface IExamenService {

	List<Examen> findAll();
	
	Examen findByExamenPorNombre(String nombre);
	
	Examen save(Examen examen);
	
	Examen findById(Long id);
	
	void deletById(Long id);
}
