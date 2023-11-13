package org.event.customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerDTO(
        @JsonProperty("first_name") String lastName,
       @JsonProperty("last_name")  String firstName,
        String gender,
        @JsonProperty("email") String email,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("id_type") String idType,
        @JsonProperty("id_value") String idValue

){

}
