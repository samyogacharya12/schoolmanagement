package com.example.schoolmanagement.mapper;

import com.example.schoolmanagement.dto.UserDto;
import com.example.schoolmanagement.entity.User;

import java.util.List;

public interface UserMapper {


    UserDto toDto(User user);

    List<UserDto> toDto(List<User> users);

    User toEntity(UserDto userDto);


}
