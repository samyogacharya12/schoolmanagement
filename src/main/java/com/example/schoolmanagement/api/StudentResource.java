package com.example.schoolmanagement.api;

import com.example.schoolmanagement.dto.StudentDto;
import com.example.schoolmanagement.dto.UserDto;
import com.example.schoolmanagement.service.StudentService;
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

    private final StudentService studentService;


    public StudentResource(UserService userService, StudentService studentService) {
        this.userService = userService;
        this.studentService = studentService;
    }


    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<StudentDto> save(@RequestBody StudentDto studentDto) {
        studentDto.setUpdatedDate(LocalDateTime.now());
        StudentDto studentDto1 = this.studentService.update(studentDto);
        return new ResponseEntity<>(studentDto1, HttpStatus.OK);
    }

    @GetMapping("/fetch")
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<StudentDto> fetchById(@RequestParam(value = "id") Long id) {
        StudentDto studentDto = this.studentService.findById(id);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
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
