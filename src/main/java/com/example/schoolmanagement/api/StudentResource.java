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


    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        userDto.setUpdatedDate(LocalDateTime.now());
        UserDto userDto1 = this.userService.update(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }

    @GetMapping("/fetch")
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<UserDto> fetchById(@RequestParam(value = "id") Long id) {
        UserDto userDto = this.userService.findById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<List<UserDto>> fetch(@RequestParam(value = "userType") String userType) {
        List<UserDto> userDtos = this.userService.findByType(userType);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<?> delete(@RequestParam(value = "id") Long id) {
        this.userService.delete(id);
        return new ResponseEntity<>("user is deleted", HttpStatus.OK);
    }


}
