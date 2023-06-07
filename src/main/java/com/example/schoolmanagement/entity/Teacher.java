package com.example.schoolmanagement.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "teacher")
public class Teacher extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName", nullable = false)
    @NotNull
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @NotNull
    private String lastName;


    @Column(name = "address", nullable = false)
    @NotNull
    private String address;


    @Column(name = "salary")
    private String salary;

    @Column(name = "subject")
    private String subject;

    @Column(name = "phoneNumber", nullable = false)
    @NotNull
    private String phoneNumber;


    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User userInfo;


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

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public Teacher(){

    }

    public Teacher(@NotNull String firstName,
                   @NotNull String lastName,
                   @NotNull String address,
                   String salary,
                   String subject,
                   @NotNull String phoneNumber,
                   User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.salary = salary;
        this.subject = subject;
        this.phoneNumber = phoneNumber;
        this.userInfo = user;
    }
}
