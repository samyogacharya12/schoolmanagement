package com.example.schoolmanagement.mapper;

import com.example.schoolmanagement.dto.RegisterUserDto;
import com.example.schoolmanagement.dto.UserDto;
import com.example.schoolmanagement.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserMapper {


    UserDto toDto(User user);

    List<UserDto> toDto(List<User> users);


    User toEntity(UserDto userDto);


    User toEntity(RegisterUserDto registerUserDto);


}
