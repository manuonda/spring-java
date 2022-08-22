package com.data.projection.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.data.projection.entity.Person;

import lombok.Data;

@Data
public class AddressDTO {

	private Long id;	
	private String state;
	private String city;
	private String street;
	private String zipCode;
}
