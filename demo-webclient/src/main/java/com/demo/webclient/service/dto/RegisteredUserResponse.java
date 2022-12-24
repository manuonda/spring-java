package com.demo.webclient.service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisteredUserResponse {

    private String status;
    private String message;
    @JsonProperty("data")
    private RegisteredDataUser registeredDataUser;
}