package com.data.projection.repository;

import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.data.projection.dto.PersonLocationDTO2;
import com.data.projection.entity.Person;
import com.data.projection.projections.PersonFullLocation;
import com.data.projection.projections.PersonLocation;

public interface PersonRepository extends JpaRepository<Person, Long> {

	
	//Close projection
	@Query(value = "SELECT " +
	"P.FIRST_NAME as firstName," +
    "P.LAST_NAME as lastName," + 
	"P.PHONE_NUMBER as phoneNumber, "+
	"A.STREET as street " + 
	" FROM PERSON P "+
	" INNER JOIN ADDRESS A ON A.ID = P.ADDRESS_ID "+
	" WHERE P.ID = :id", nativeQuery=true)
    PersonLocation getPersonLocation(@Param("id") Long personId);
	
	//Open Projection Sample
	@Query(value = "SELECT " +
	"P.FIRST_NAME as firstName," +
    "P.LAST_NAME as lastName," + 
	"P.PHONE_NUMBER as phoneNumber, "+
	"A.STREET as street " + 
	" FROM PERSON P "+
	" INNER JOIN ADDRESS A ON A.ID = P.ADDRESS_ID "+
	" WHERE P.ID = :id", nativeQuery=true)
    PersonFullLocation getPersonFullLocation(@Param("id") Long personId);
	
	// Using class based
	@Query(value = "SELECT " +
			"P.FIRST_NAME as firstName," +
		    "P.LAST_NAME as lastName," + 
			"P.PHONE_NUMBER as phoneNumber, "+
			"A.STREET as street " + 
			" FROM PERSON P "+
			" INNER JOIN ADDRESS A ON A.ID = P.ADDRESS_ID "+
			" WHERE P.ID = :id", nativeQuery=true) 
	Tuple getPersonLocationDTO(@Param("id") Long personId);
	
	
	// using named query 
	@Query(name="getPersonLocationDTONamingQuery", nativeQuery = true)
	public PersonLocationDTO2 getPersonLocationDTO2(@Param("id") Long personId);
	
	// dynamically
	@Query(value = "SELECT " +
			"P.FIRST_NAME as firstName," +
		    "P.LAST_NAME as lastName," + 
			"P.PHONE_NUMBER as phoneNumber, "+
			"A.STREET as street " + 
			" FROM PERSON P "+
			" INNER JOIN ADDRESS A ON A.ID = P.ADDRESS_ID "+
			" WHERE P.ID = :id", nativeQuery=true) 
	<T> T getPersonLocationDynamically(@Param("id") Long personId, Class<T> type);
	
	
}
