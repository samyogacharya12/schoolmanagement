package com.example.schoolmanagement.service;

import com.example.schoolmanagement.dto.RegisterUserDto;
import com.example.schoolmanagement.dto.TeacherDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeacherService {


    TeacherDto save(RegisterUserDto teacherDto);

    TeacherDto update(TeacherDto TeacherDto);

    TeacherDto findById(Long id);

    List<TeacherDto> findAll();

    void delete(Long id);
}
