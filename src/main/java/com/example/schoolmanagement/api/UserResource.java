package com.example.schoolmanagement.api;

import com.example.schoolmanagement.dto.RegisterUserDto;
import com.example.schoolmanagement.dto.UserDto;
import com.example.schoolmanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {


    private final UserService userService;


    public UserResource(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/save")
    public ResponseEntity<RegisterUserDto> addNewUser(@RequestBody RegisterUserDto registerUserDto) {
        registerUserDto.setCreatedDate(LocalDateTime.now());
        registerUserDto.setUpdatedDate(LocalDateTime.now());
        RegisterUserDto user = this.userService.save(registerUserDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> userDtos = this.userService.findAll();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }


}
