package com.example.schoolmanagement.mapper;

import com.example.schoolmanagement.dto.RegisterUserDto;
import com.example.schoolmanagement.dto.StudentDto;
import com.example.schoolmanagement.entity.Student;
import com.example.schoolmanagement.entity.User;
import com.example.schoolmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentMapperImpl implements StudentMapper {


    public final UserRepository userRepository;


    private StudentMapperImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    private static StudentDto mapToStudentDto(Student student) {

        StudentDto studentDto = new StudentDto(student.getId(),
                student.getFirstName(),
                student.getLastName()
                , student.getAddress(), student.getGrade(),
                student.getStandard(),
                student.getPhoneNumber(),
                student.getUser().getId(),
                student.getUser().getName());
        studentDto.setCreatedDate(student.getCreatedDate());
        studentDto.setUpdatedDate(student.getUpdatedDate());
        return studentDto;
    }


    @Override
    public StudentDto toDto(Student student) {
        return mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> toDto(List<Student> studentList) {
        return studentList.stream().map(StudentMapperImpl::mapToStudentDto).collect(Collectors.toList());
    }

    @Override
    public Student toEntity(StudentDto studentDto) {
        Optional<User> user = this.userRepository.findById(studentDto.getUserId());
        Student student;
        if (user.isPresent()) {
            student = new Student(studentDto.getFirstName(),
                    studentDto.getLastName(),
                    studentDto.getAddress(),
                    studentDto.getGrade(),
                    studentDto.getStandard(),
                    studentDto.getPhoneNumber(),
                    user.get());
            student.setId(studentDto.getId());
            student.setCreatedDate(studentDto.getCreatedDate());
            student.setUpdatedDate(studentDto.getUpdatedDate());
            student.setStatus(studentDto.isStatus());
            student.setDeleted(studentDto.isDeleted());
            return student;
        }
        return new Student();
    }

    @Override
    public StudentDto toDto(RegisterUserDto registerUserDto) {
        StudentDto studentDto = new StudentDto(registerUserDto.getId(),
                registerUserDto.getFirstName(),
                registerUserDto.getLastName()
                , registerUserDto.getAddress(), registerUserDto.getGrade(),
                registerUserDto.getStandard(),
                registerUserDto.getPhoneNumber(),
                registerUserDto.getUserId(),
                registerUserDto.getUserName());
        studentDto.setCreatedDate(registerUserDto.getCreatedDate());
        studentDto.setUpdatedDate(registerUserDto.getUpdatedDate());
        return studentDto;
    }
}
