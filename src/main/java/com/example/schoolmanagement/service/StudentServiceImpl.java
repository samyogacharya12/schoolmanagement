package com.example.schoolmanagement.service;

import com.example.schoolmanagement.dto.RegisterUserDto;
import com.example.schoolmanagement.dto.StudentDto;
import com.example.schoolmanagement.entity.Student;
import com.example.schoolmanagement.mapper.StudentMapper;
import com.example.schoolmanagement.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);


    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private StudentMapper studentMapper;

    @Override
    public StudentDto save(RegisterUserDto registerUserDto) {
        log.info("saving students");
        StudentDto studentDto=this.studentMapper.toDto(registerUserDto);
        Student student = this.studentMapper.toEntity(studentDto);
        return this.studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    @CachePut(cacheNames = "student", key = "#studentDto.id")
    public StudentDto update(StudentDto studentDto) {
        log.info("updating students ");
        Student student = this.studentMapper.toEntity(studentDto);
        return this.studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    @Cacheable(cacheNames = "student", key = "#id")
    public StudentDto findById(Long id) {
        Optional<StudentDto> studentDto = this.studentRepository.findById(id).map(studentMapper::toDto);
        if (studentDto.isEmpty()) {
            throw new UsernameNotFoundException("User not found" + id);
        }
        return studentDto.get();
    }

    @Override
    public List<StudentDto> findAll() {
        log.info("fetching students from database");
        return this.studentRepository.findAll().stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "student", key = "#id")
    public void delete(Long id) {
        log.info("delete student");
        this.studentRepository.deleteById(id);
    }
}
