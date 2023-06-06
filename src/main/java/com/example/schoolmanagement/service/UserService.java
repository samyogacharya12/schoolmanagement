package com.example.schoolmanagement.service;

import com.example.schoolmanagement.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto saver(UserDto userDto);

    UserDto findById(Long id);

    void delete(Long id);

    List<UserDto> findAll();

    List<UserDto> findByType(String userType);
}
