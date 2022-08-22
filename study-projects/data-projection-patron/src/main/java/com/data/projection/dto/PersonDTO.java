package com.data.projection.dto;

import javax.persistence.OneToOne;

import com.data.projection.entity.Address;

import lombok.Data;


@Data
public class PersonDTO {
  
    private Long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Address address;
	
}
