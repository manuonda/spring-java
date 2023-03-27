package org.appmockito.ejemplos.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;

import org.appmockito.ejemplos.models.Examen;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ExamenRepositoryTest {

	Examen examen ;
	
	@Autowired
	private EntityManager manager;
	
	@Autowired
	private IExamenRepository repository;
	
	@BeforeEach
	public void init() {
		examen = Examen.builder().nombre("Examen1").build();
    }
	
	@Test 
	@DisplayName("List of Objects Examen repository")
	public void testGivenListExamen_whenGetAllExamen_thenReturnExamenListObject() {
		//given
		examen = Examen.builder().nombre("Examen Fisica").build();
		Examen examenMatematica = Examen.builder().nombre("Examen Math").build();
		repository.save(examen);
		repository.save(examenMatematica);
		
		//when
     	List<Examen> examenList = repository.findAll();
	    
     	//then
		assertNotNull(examenList);
		assertEquals(2, examenList.size());
		Assertions.assertThat(examenList.size()).isGreaterThan(0);
		
	}
	
	@Test
	@DisplayName("Save Examen")
	public void testGivenExamen_whenSaveExamen() {
		// given
		
		//when
		Examen examenSave = this.repository.save(examen);
		//when(repository.save(any(Examen.class))).thenReturn(examen2);
		
		//then 
		assertThat(examenSave).isNotNull();
		assertThat(examenSave.getId()).isGreaterThan(0);
	}
	
	
     @Test
     @DisplayName("Update Object")
     public void testGivenExamenObject_whenUpdateObject_thenReturnsUpdateExamen() {
    	 Examen examenFind = null;
    	 
    	 //given 
         
    	 //when
    	 Examen examenSave= this.repository.save(examen);
    	 Optional<Examen> optional= this.repository.findById(examenSave.getId());
    	 
         
         if (optional.isPresent()) {
        	 examenFind = optional.get();
        	 examenFind.setNombre("Ambiente Social");
        	 examenSave = this.repository.save(examenFind);
         }
    	 //then
        assertNotNull(examenSave);
        assertThat(examenFind.getId()).isGreaterThan(0);
        assertEquals(examenSave.getNombre(), "Ambiente Social");
        assertThat(examenSave.getId()).isEqualTo(7L);
     }
     
     @Test 
     @DisplayName("Find Object by Nombre")
     public void testGivenObject_whenFindByNombre_thenReturnsObjectExamen() {
    	 //given
    	  examen = Examen.builder().nombre("Prueba").build();
    	  
    	 //when 
    	  this.repository.save(examen);
    	  Examen examenFindByNombre =  this.repository.findByNombre("Prueba");
    	  
    	 //then
    	  assertThat(examenFindByNombre).isNotNull();
    	  assertNotNull(examenFindByNombre);
    	  assertThat(examenFindByNombre.getNombre()).isEqualTo("Prueba");
     }
	
     
     @Test 
     @DisplayName("ThrowException find by nombre not exist")
     public void testGivenObject_whenFindByNombre_thenReturnThrowException(){
    	 //given 
    	 examen = Examen.builder().nombre("prueba").build();
    	 //when
    	 this.repository.save(examen);
    	
    	 
    	 //then
    	 org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class,()-> {
    		 Examen examenFindByNombre = this.repository.findByNombre("informacion");
    	     examenFindByNombre.getNombre();
    	 }, "Not elemento found by nombre informacion");
     }
     
     @Test
     @DisplayName("Delete object Examen")
     public void testGivenObject_whenDeleteObjectById_thenNotFoundObject() {
    	 //given 
    	 examen = Examen.builder().nombre("informacion").build();
    	 //when 
    	 this.repository.save(examen);
    	 this.repository.delete(examen);
    	 
    	 assertThrows(NoSuchElementException.class, () ->{
    		 repository.findById(1L);
    	 },"Not Found Examen 1L");
     }
		
	
  
}
