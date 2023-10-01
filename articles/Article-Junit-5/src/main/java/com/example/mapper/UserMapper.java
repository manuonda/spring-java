package com.example.mapper;


import com.example.domain.User;
import com.example.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);
   // @Mapping(target = "password" , ignore = true)
    User toUser(UserDTO userDTO);
}
