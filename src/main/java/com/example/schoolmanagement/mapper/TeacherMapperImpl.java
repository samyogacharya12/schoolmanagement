package com.example.schoolmanagement.mapper;

import com.example.schoolmanagement.dto.RegisterUserDto;
import com.example.schoolmanagement.dto.TeacherDto;
import com.example.schoolmanagement.entity.Teacher;
import com.example.schoolmanagement.entity.User;
import com.example.schoolmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherMapperImpl implements TeacherMapper {

    public final UserRepository userRepository;


    private TeacherMapperImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    private static TeacherDto mapToTeacherDto(Teacher teacher) {

        TeacherDto teacherDto = new TeacherDto(teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName()
                , teacher.getAddress(),
                teacher.getSalary(),
                teacher.getSubject(),
                teacher.getPhoneNumber(),
                teacher.getUserInfo().getId(),
                teacher.getUserInfo().getName());
        teacherDto.setCreatedDate(teacher.getCreatedDate());
        teacherDto.setUpdatedDate(teacher.getUpdatedDate());
        return teacherDto;
    }


    @Override
    public TeacherDto toDto(Teacher teacher) {
        return mapToTeacherDto(teacher);
    }

    @Override
    public TeacherDto toDto(RegisterUserDto registerUserDto) {
        TeacherDto teacherDto = new TeacherDto(registerUserDto.getId(),
                registerUserDto.getFirstName(),
                registerUserDto.getLastName()
                , registerUserDto.getAddress(),
                registerUserDto.getSalary(),
                registerUserDto.getSubject(),
                registerUserDto.getPhoneNumber(),
                registerUserDto.getUserId(),
                registerUserDto.getUserName());
        teacherDto.setCreatedDate(registerUserDto.getCreatedDate());
        teacherDto.setUpdatedDate(registerUserDto.getUpdatedDate());
        return teacherDto;
    }

    @Override
    public List<TeacherDto> toDto(List<Teacher> teacherList) {
        return teacherList.stream().map(TeacherMapperImpl::mapToTeacherDto).collect(Collectors.toList());
    }

    @Override
    public Teacher toEntity(TeacherDto teacherDto) {
        Optional<User> user = this.userRepository.findById(teacherDto.getUserId());
        Teacher teacher;
        if (user.isPresent()) {
            teacher = new Teacher(teacherDto.getFirstName(),
                    teacherDto.getLastName(),
                    teacherDto.getAddress(),
                    teacherDto.getSalary(),
                    teacherDto.getSubject(),
                    teacherDto.getPhoneNumber(),
                    user.get());
            teacher.setId(teacherDto.getId());
            teacher.setCreatedDate(teacherDto.getCreatedDate());
            teacher.setUpdatedDate(teacherDto.getUpdatedDate());
            teacher.setStatus(teacherDto.isStatus());
            teacher.setDeleted(teacherDto.isDeleted());
            return teacher;
        }
        return new Teacher();
    }
}
