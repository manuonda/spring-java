package org.appmockito.ejemplos.services;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.appmockito.ejemplos.models.Examen;
import org.appmockito.ejemplos.repository.IExamenRepository;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.hibernate.loader.plan.spi.AnyAttributeFetch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;



@ExtendWith(MockitoExtension.class)
public class ExamenServiceImplTest {

	
	@InjectMocks
	ExamenServiceImpl examenServiceImpl;
	
	
	@Mock
	IExamenRepository iExamenRepository;
	
	@Captor
	ArgumentCaptor<String> captor;
	
	Examen examen;
	
	List<Examen> examenes = new ArrayList<>();

	@BeforeEach
	public void init() {
	  examen = Examen.builder()
			  .id(1L)
			  .nombre("Examen Fisica")
			  .build();
	  //Examen newExamen = this.examenServiceImpl.save(examen);
	  examenes.add(examen);
	  
	  examen = Examen.builder()
			  .id(2L)
			  .nombre("Examen Matematica")
			  .build();
	  //newExamen = this.examenServiceImpl.save(examen);
	  examenes.add(examen);
	  
	}
	
	@Test
	@DisplayName("Find Examen by Name")
	public void testFindByExamen_whenFindByName() {
		//given - dado o condicion previa de configuracion
		Optional<Examen> examen = Optional.of(Examen.builder().id(1L).nombre("David").build());
		
		//when accion o el comportamiento que vamos a probar 
	    when(iExamenRepository.findByNombre("Examen Matematica")).thenReturn(examen);
	    Optional<Examen> optional = this.examenServiceImpl.findByExamenPorNombre("Examen Matematica");
		
	    //then - verificar la salida 
	    assertTrue(optional.isPresent(),"Examen is found");
	}

	
	
	@Test
	@DisplayName("Save Examen")
	public void testGiveExamen_whenSaveExamen() {
		//given
		 Examen examen = Examen.builder().id(23L).nombre("Fisica").build();
		 when(this.iExamenRepository.save(any(Examen.class))).thenReturn(examen);
		 
		 //when
		 Examen newExamen = this.examenServiceImpl.save(examen);
		
		//then
		 assertNotNull(newExamen,"Examen is not null");
		 assertTrue(newExamen.getId()>0);
		
	}
	
	@Test
	@DisplayName("Test Verify Examenes")
	public void testGivenVerifiy_whenGetAllExamenes_thenReturnExamenes() {
		
		//Given
		when(this.iExamenRepository.findAll()).thenReturn(this.examenes);
		//when
		List<Examen> examenes =this.examenServiceImpl.findAll();
		
		//Then
		assertEquals(2, examenes.size());
		verify(this.iExamenRepository).findAll(); //veify permite establecer si se ejecuta el metodo
		
	}
	
	@Test
	@DisplayName("Test Save Examen Id Incremental")
	public void testGivenUpdate_whenUpdateExamen_thenReturnExamen() {
	   //given -- precodnitions
		Examen examen = Examen.builder().id(1L).nombre("Matematica").build();
	  
	   
		//Se modifica el metodo para botener el Id de manera incremental
		when(this.iExamenRepository.save(any(Examen.class))).then(new Answer<Examen>() {
			Long secuencia = 8L;
		    @Override
		    public Examen answer(InvocationOnMock invocation) throws Throwable {
                Examen examen =  invocation.getArgument(0); //Obtengo el exmaen class pass argument
		    	examen.setId(++secuencia);
                return examen;
		    }
		});
	    examen.setNombre("David Garcia");
		//when
	    Examen newExamen = this.examenServiceImpl.save(examen);
		
		//then 
		assertNotNull(newExamen);
		assertEquals(9L,newExamen.getId());
		assertNotEquals("Matematica",newExamen.getNombre());
		
		verify(this.iExamenRepository).save(any(Examen.class));
		
	}
	
	@Test 
	@DisplayName("Test Examen FindByExamen then Return Exception")
	public void testExamen_whenFinByExamenPorNombre_thenReturnException() {
		//given 
		//when(this.iExamenRepository.findAll()).thenReturn(examenes);
		Optional<Examen> examen = Optional.of( Examen.builder().nombre("Matematica").build());
		when(this.iExamenRepository.findByNombre("Matematica")).thenReturn(examen);
		
//		Exception exception  = assertThrows(IllegalArgumentException.class, () ->{
//			 this.examenServiceImpl.findByExamenPorNombre("3");  
//			 throw new IllegalArgumentException("message");
//
//		},"Not found Examen Informacion"); 
		
		Optional<Examen> optional = this.examenServiceImpl.findByExamenPorNombre("Informacion");
		
		//assertEquals(IllegalArgumentException.class,exception.getClass());
		//assertTrue(optional.isPresent());
		//verify(this.iExamenRepository).findByNombre("INformacion");
		
	}
	
	@Test
	@DisplayName("test Argument Matchers")
	public void testArgumentMatchers() {
		Optional<Examen> optionalExamen = Optional.of( Examen.builder().id(23L).nombre("Matematica").build());

		when(this.iExamenRepository.save(any(Examen.class))).thenReturn(optionalExamen.get());
		when(this.iExamenRepository.findByNombre(anyString())).thenReturn(optionalExamen);
		
		Examen newExamen = this.examenServiceImpl.save(optionalExamen.get());
		Optional<Examen> examenByNombre = this.examenServiceImpl.findByExamenPorNombre("Matematica");
		
		verify(this.iExamenRepository).save(any(Examen.class));
		verify(this.iExamenRepository).findByNombre(argThat(arg ->arg != null  &&  arg != "" && arg == "Matematica"));
		
	}
	
	@Test
	@DisplayName("test Argument Captor")
	public void testArgumentCaptor() {
		Optional<Examen> optionalExamen = Optional.of( Examen.builder().id(23L).nombre("Matematica").build());

		when(this.iExamenRepository.save(any(Examen.class))).thenReturn(optionalExamen.get());
		when(this.iExamenRepository.findByNombre(anyString())).thenReturn(optionalExamen);
		
		// Save first
		Examen newExamen = this.examenServiceImpl.save(optionalExamen.get());
		// find later
		Optional<Examen> examenByNombre = this.examenServiceImpl.findByExamenPorNombre("Matematica");
		
		// ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(this.iExamenRepository).findByNombre(captor.capture());
		
		assertEquals("Matematica", captor.getValue());
		
	}
}
