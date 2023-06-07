package com.example.schoolmanagement.api;

import com.example.schoolmanagement.dto.TeacherDto;
import com.example.schoolmanagement.dto.UserDto;
import com.example.schoolmanagement.service.TeacherService;
import com.example.schoolmanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherResource {

    private final UserService userService;

    private final TeacherService teacherService;


    public TeacherResource(UserService userService,TeacherService teacherService) {
        this.userService = userService;
        this.teacherService=teacherService;
    }


    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public ResponseEntity<TeacherDto> save(@RequestBody TeacherDto teacherDto) {
        teacherDto.setUpdatedDate(LocalDateTime.now());
        TeacherDto teacherDto1 = this.teacherService.update(teacherDto);
        return new ResponseEntity<>(teacherDto1, HttpStatus.OK);
    }


    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public ResponseEntity<List<UserDto>> fetch(@RequestParam(value = "userType") String userType) {
        List<UserDto> userDtos = this.userService.findByType(userType);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }


    @GetMapping("/fetch")
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public ResponseEntity<TeacherDto> fetchById(@RequestParam(value = "id") Long id) {
        TeacherDto teacherDto = this.teacherService.findById(id);
        return new ResponseEntity<>(teacherDto, HttpStatus.OK);
    }



    @DeleteMapping
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public ResponseEntity<?> delete(@RequestParam(value = "id") Long id) {
        this.userService.delete(id);
        return new ResponseEntity<>("user is deleted", HttpStatus.OK);
    }
}
