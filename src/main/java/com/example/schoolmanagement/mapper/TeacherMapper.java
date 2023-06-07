package com.example.schoolmanagement.mapper;

import com.example.schoolmanagement.dto.RegisterUserDto;
import com.example.schoolmanagement.dto.TeacherDto;
import com.example.schoolmanagement.entity.Teacher;

import java.util.List;

public interface TeacherMapper {

    TeacherDto toDto(Teacher teacher);

    TeacherDto toDto(RegisterUserDto registerUserDto);


    List<TeacherDto> toDto(List<Teacher> teacherList);

    Teacher toEntity(TeacherDto teacherDto);
}
