package com.example.schoolmanagement.entity;


import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student extends  BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName",nullable = false)
    @NotNull
    private String firstName;

    @Column(name = "lastName",nullable = false)
    @NotNull
    private String lastName;


    @Column(name = "address",nullable = false)
    @NotNull
    private String address;


    @Column(name = "grade")
    private String grade;

    @Column(name = "standard")
    private String standard;

    @Column(name = "phoneNumber",nullable = false)
    @NotNull
    private String phoneNumber;


    @OneToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Payment> studentsPaymentAccount;


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Payment> teachersPaymentAccount;


    public List<Payment> getStudentsPaymentAccount() {
        return studentsPaymentAccount;
    }

    public void setStudentsPaymentAccount(List<Payment> studentsPaymentAccount) {
        this.studentsPaymentAccount = studentsPaymentAccount;
    }

    public List<Payment> getTeachersPaymentAccount() {
        return teachersPaymentAccount;
    }

    public void setTeachersPaymentAccount(List<Payment> teachersPaymentAccount) {
        this.teachersPaymentAccount = teachersPaymentAccount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Student(){

    }

    public Student(@NotNull String firstName, @NotNull String lastName, @NotNull String address, String grade, String standard, @NotNull String phoneNumber, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.grade = grade;
        this.standard = standard;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }
}
