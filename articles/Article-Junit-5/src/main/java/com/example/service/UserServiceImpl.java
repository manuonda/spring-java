package com.example.service;


import com.example.config.JWTAuthProvider;
import com.example.domain.User;
import com.example.dto.CredentialsDTO;
import com.example.dto.UserDTO;
import com.example.exception.AppException;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDTO login(UserDTO dto) {
        User user = this.userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        System.out.println("user : {}  " +  user.toString());
        System.out.println("dto : {}" + dto.toString());
        if( passwordEncoder.matches(CharBuffer.wrap(dto.getPassword()), user.getPassword())) {
           return userMapper.toUserDTO(user);
        }
        throw new AppException("Invalid Password", HttpStatus.BAD_REQUEST);
    }

    public UserDTO register(UserDTO userDTO) {
        Optional<User> optionalUser = this.userRepository.findByUsername(userDTO.getUsername());
        if (optionalUser.isPresent() ) {
            throw new AppException("Username exist", HttpStatus.CONFLICT);
        }

            User user = this.userMapper.toUser(userDTO);
            user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDTO.getPassword())));
            User userSave = this.userRepository.save(user);
            return this.userMapper.toUserDTO(userSave);


    }
}
