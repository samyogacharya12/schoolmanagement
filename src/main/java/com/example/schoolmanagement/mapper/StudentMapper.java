package com.example.schoolmanagement.mapper;

import com.example.schoolmanagement.dto.RegisterUserDto;
import com.example.schoolmanagement.dto.StudentDto;
import com.example.schoolmanagement.entity.Student;

import java.util.List;


public interface StudentMapper {

    StudentDto toDto(Student student);

    List<StudentDto> toDto(List<Student> studentList);

    Student toEntity(StudentDto studentDto);

    StudentDto toDto(RegisterUserDto registerUserDto);

}
