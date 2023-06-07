package com.example.schoolmanagement.dto;

import java.io.Serializable;

public class TeacherDto extends BaseDto implements Serializable {


    private Long id;

    private String firstName;

    private String lastName;

    private String address;


    private String salary;

    private String subject;


    private String phoneNumber;

    private Long userId;

    private String userName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TeacherDto(Long id,
                      String firstName,
                      String lastName,
                      String address,
                      String salary,
                      String subject,
                      String phoneNumber,
                      Long userId,
                      String userName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.salary = salary;
        this.subject = subject;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.userName = userName;
    }
}
