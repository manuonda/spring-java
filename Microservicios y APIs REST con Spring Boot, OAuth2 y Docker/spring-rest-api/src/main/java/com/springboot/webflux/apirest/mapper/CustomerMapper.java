package com.springboot.webflux.apirest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.springboot.webflux.apirest.dto.CustomerDTO;
import com.springboot.webflux.apirest.models.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {

	CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

	@Mapping( source = "name", target="nameCustomer" )
	CustomerDTO toDTO(Customer entity);
	
	@Mapping(source="nameCustomer", target="name")
	Customer toEntity(CustomerDTO dto);
}
