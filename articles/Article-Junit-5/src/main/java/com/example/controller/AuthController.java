package com.example.controller;

import com.example.config.JWTAuthProvider;
import com.example.dto.UserDTO;
import com.example.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserServiceImpl userService;
    private final JWTAuthProvider jwtAuthProvider;


    public AuthController(UserServiceImpl userService, JWTAuthProvider jwtAuthProvider) {
        this.userService = userService;
        this.jwtAuthProvider = jwtAuthProvider;
    }


    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.login(userDTO);
        user.setToken(jwtAuthProvider.createToken(userDTO));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO userDTO) {
        UserDTO registerDTO = this.userService.register(userDTO);
        registerDTO.setToken(jwtAuthProvider.createToken(registerDTO));
        return ResponseEntity.created(URI.create("/users/"+ registerDTO.getId())).body(registerDTO);
    }

}
