package com.example.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Data
public class UserDTO {

    Integer id;
    String username;
    String lastName;
    String password;

    String token;
    String login;

}
