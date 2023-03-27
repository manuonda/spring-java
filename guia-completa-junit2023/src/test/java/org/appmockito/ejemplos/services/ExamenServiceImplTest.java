package org.appmockito.ejemplos.services;

import org.appmockito.ejemplos.models.Examen;
import org.appmockito.ejemplos.repository.IExamenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;


@ExtendWith(MockitoExtension.class)
public class ExamenServiceImplTest {

	@InjectMocks
	IExamenService iExamenService;
	
	@Mock
	IExamenRepository iExamenRepository;
	
	Examen examen;

	@BeforeEach
	public void init() {
	  examen = Examen.builder()
			  .nombre("Examen Fisica")
			  .build();
	}
	
	@Test
	@DisplayName("Find Examen by Name")
	public void testFindByExamen_whenFindByName() {
		//given - dado o condicion previa de configuracion
		
		//when accion o el comportamiento que vamos a probar 
		 Examen examenSaved=
		//then - verificar la salida 
	}
	
	@Test
	@DisplayName("Save Examen")
	public void testGiveExamen_whenSaveExamen() {
		//Arrange
		
	}
}
