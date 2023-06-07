package com.example.schoolmanagement.mapper;

import com.example.schoolmanagement.dto.RegisterUserDto;
import com.example.schoolmanagement.dto.UserDto;
import com.example.schoolmanagement.entity.User;
import com.example.schoolmanagement.enumconstants.UserType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapperImpl implements UserMapper {


    @Override
    public UserDto toDto(User user) {
        return mapToUserDto(user);
    }

    private static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto(user.getId(),
                user.getName()
                , user.getEmail(), user.getPassword(),
                user.getRoles().toString());
        userDto.setCreatedDate(user.getCreatedDate());
        userDto.setUpdatedDate(user.getUpdatedDate());
        return userDto;
    }


    @Override
    public List<UserDto> toDto(List<User> users) {
        return users.stream().map(UserMapperImpl::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getEmail(),
                userDto.getPassword(), Enum.valueOf(UserType.class, userDto.getRoles()));
        user.setId(userDto.getId());
        user.setCreatedDate(userDto.getCreatedDate());
        user.setUpdatedDate(userDto.getUpdatedDate());
        user.setStatus(userDto.isStatus());
        user.setDeleted(userDto.isDeleted());
        user.setId(user.getId());
        user.setCreatedDate(userDto.getCreatedDate());
        user.setUpdatedDate(userDto.getUpdatedDate());
        return user;
    }

    @Override
    public User toEntity(RegisterUserDto registerUserDto) {
        User user = new User(registerUserDto.getName(), registerUserDto.getEmail(),
                registerUserDto.getPassword(), Enum.valueOf(UserType.class, registerUserDto.getRoles()));
        user.setId(registerUserDto.getId());
        user.setCreatedDate(registerUserDto.getCreatedDate());
        user.setUpdatedDate(registerUserDto.getUpdatedDate());
        user.setStatus(registerUserDto.isStatus());
        user.setDeleted(registerUserDto.isDeleted());
        return user;
    }
}
