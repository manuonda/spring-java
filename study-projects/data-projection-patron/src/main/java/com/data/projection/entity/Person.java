package com.data.projection.entity;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.data.projection.dto.PersonLocationDTO2;

import lombok.Data;

@Data
@Entity
@Table(name="person")
@NamedNativeQuery(
		name = "getPersonLocationDTONamingQuery",
		query="SELECT " +
				"P.FIRST_NAME as name," +
			    "P.PHONE_NUMBER as phoneNumber, "+
				"A.STREET as street " + 
				" FROM PERSON P "+
				" INNER JOIN ADDRESS A ON A.ID = P.ADDRESS_ID "+
				" WHERE P.ID = :id",
		resultSetMapping = "PersonLocationDTO2Mapping"
)
@SqlResultSetMapping(
		name="PersonLocationDTO2Mapping",
		classes = @ConstructorResult(
				targetClass = PersonLocationDTO2.class,
				columns = {
					@ColumnResult(name="name" , type=String.class),
					@ColumnResult(name="phone_number", type= String.class),
					@ColumnResult(name="street", type= String.class)
				}
))

public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	
	@OneToOne
	private Address address;
}
