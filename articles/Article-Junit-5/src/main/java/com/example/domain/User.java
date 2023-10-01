package com.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="app_userr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty(message = "Username not empty")
    @Column(name="user_name")
    private String username;

    @Column(name="last_name")
    private String lastName;



    @NotBlank(message = "Not Empty Password")
    @Column(nullable = false)
    private String password;

}
