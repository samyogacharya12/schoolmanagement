package com.example.schoolmanagement.api;

import com.example.schoolmanagement.dto.UserDto;
import com.example.schoolmanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentResource {

    private final UserService userService;


    public StudentResource(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        userDto.setUpdatedDate(LocalDateTime.now());
        UserDto userDto1 = this.userService.saver(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }


    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<List<UserDto>> fetch() {
        List<UserDto> userDtos = this.userService.findByType();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }


}
