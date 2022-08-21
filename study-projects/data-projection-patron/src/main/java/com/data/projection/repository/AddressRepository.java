package com.data.projection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.projection.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
