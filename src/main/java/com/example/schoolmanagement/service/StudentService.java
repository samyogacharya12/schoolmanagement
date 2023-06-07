package com.example.schoolmanagement.service;


import com.example.schoolmanagement.dto.RegisterUserDto;
import com.example.schoolmanagement.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto save(RegisterUserDto studentDto);

    StudentDto update(StudentDto studentDto);

    StudentDto findById(Long id);

    List<StudentDto> findAll();

    void delete(Long id);
}
