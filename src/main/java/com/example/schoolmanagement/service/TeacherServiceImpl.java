package com.example.schoolmanagement.service;

import com.example.schoolmanagement.dto.RegisterUserDto;
import com.example.schoolmanagement.dto.TeacherDto;
import com.example.schoolmanagement.entity.Teacher;
import com.example.schoolmanagement.mapper.TeacherMapper;
import com.example.schoolmanagement.repository.PaymentRepository;
import com.example.schoolmanagement.repository.TeacherRepository;
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
public class TeacherServiceImpl implements TeacherService {


    private final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);


    @Autowired
    private TeacherRepository teacherRepository;


    @Autowired
    private TeacherMapper teacherMapper;


    @Override
    public TeacherDto save(RegisterUserDto registerUserDto) {
        log.info("saving teachers");
        TeacherDto teacherDto = this.teacherMapper.toDto(registerUserDto);
        Teacher teacher = this.teacherMapper.toEntity(teacherDto);
        return teacherMapper.toDto(teacherRepository.save(teacher));
    }

    @Override
    @CachePut(cacheNames = "teacher", key = "#teacherDto.id")
    public TeacherDto update(TeacherDto teacherDto) {
        log.info("updating teachers");
        Teacher teacher = this.teacherMapper.toEntity(teacherDto);
        return this.teacherMapper.toDto(teacherRepository.save(teacher));
    }

    @Override
    @Cacheable(cacheNames = "teacher", key = "#id")
    public TeacherDto findById(Long id) {
        log.info("fetching teacher from database");
        Optional<TeacherDto> teacher = teacherRepository.findById(id).map(teacherMapper::toDto);
        if (teacher.isEmpty()) {
            throw new UsernameNotFoundException("User not found" + id);
        }
        return teacher.get();
    }

    @Override
    public List<TeacherDto> findAll() {
        log.info("fetching teachers from database");
        return this.teacherRepository.findAll().stream().map(teacherMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "teacher", key = "#id")
    public void delete(Long id) {
        log.info("delete teacher");
        this.teacherRepository.deleteById(id);
    }
}
