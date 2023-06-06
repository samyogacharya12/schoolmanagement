package com.example.schoolmanagement.mapper;

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
        return new UserDto(user.getId(),
                user.getName()
                , user.getEmail(),user.getPassword(),
                user.getRoles().toString());
    }


    @Override
    public List<UserDto> toDto(List<User> users) {
        return users.stream().map(UserMapperImpl::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getEmail(),
                userDto.getPassword(), Enum.valueOf(UserType.class, userDto.getRoles()));
        user.setCreatedDate(userDto.getCreatedDate());
        user.setUpdatedDate(userDto.getUpdatedDate());
        user.setStatus(userDto.isStatus());
        user.setDeleted(userDto.isDeleted());
        return user;
    }
}
