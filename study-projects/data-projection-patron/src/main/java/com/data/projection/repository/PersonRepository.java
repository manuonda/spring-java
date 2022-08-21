package com.data.projection.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.data.projection.entity.Person;
import com.data.projection.projections.PersonLocation;

public interface PersonRepository extends JpaRepository<Person, Long> {

	
	// Close Projection
	// select firstName, lastName from person inner join address a on
	// a.id = p.address_id
	// where p.id := ?1
	//PersonLocation findByUserName(String firstName);
	
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
}
