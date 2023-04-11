package org.appmockito.ejemplos.services;


import java.util.List;
import java.util.Optional;

import org.appmockito.ejemplos.models.Examen;
import org.appmockito.ejemplos.repository.IExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExamenServiceImpl implements IExamenService {

    @Autowired
	private IExamenRepository repository;

	
	@Override
	public List<Examen> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Optional<Examen> findByExamenPorNombre(String nombre) {
		return this.repository.findByNombre(nombre);
	
	}

	@Override
	public Examen save(Examen examen) {
	
		return this.repository.save(examen);	
	}

	@Override
	public Examen findById(Long id) {
		Optional<Examen> optional = this.repository.findById(id);
		Examen examen = null;
		if(optional.isPresent()) {
			examen = optional.get();
		}
		return examen;
	}

	@Override
	public void deletById(Long id) {
		this.repository.deleteById(id);
	}
	
	
	
	
}
