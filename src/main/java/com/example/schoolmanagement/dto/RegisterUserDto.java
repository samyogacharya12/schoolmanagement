package com.example.schoolmanagement.dto;

import java.io.Serializable;

public class RegisterUserDto extends BaseDto implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String roles;
    private String firstName;
    private String lastName;
    private String address;
    private String salary;
    private String subject;
    private String phoneNumber;
    private String grade;
    private String standard;
    private Long userId;
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RegisterUserDto(){

    }

    public RegisterUserDto(StudentDto studentDto) {
        this.firstName = studentDto.getFirstName();
        this.lastName = studentDto.getLastName();
        this.address = studentDto.getAddress();
        this.standard = studentDto.getStandard();
        this.grade = studentDto.getGrade();
        this.phoneNumber = studentDto.getPhoneNumber();
        this.userId = studentDto.getUserId();
    }

    public RegisterUserDto(TeacherDto teacherDto) {
        this.firstName = teacherDto.getFirstName();
        this.lastName = teacherDto.getLastName();
        this.address = teacherDto.getAddress();
        this.subject = teacherDto.getSubject();
        this.salary = teacherDto.getSalary();
        this.phoneNumber = teacherDto.getPhoneNumber();
        this.userId = teacherDto.getUserId();
    }
}
