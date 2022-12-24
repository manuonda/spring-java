package com.demo.webclient.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginResponse {

	private String status;
    @JsonProperty("data")
    private RegisteredDataUser registeredDataUser;
}
