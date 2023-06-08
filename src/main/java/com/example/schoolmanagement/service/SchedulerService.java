package com.example.schoolmanagement.service;

import com.example.schoolmanagement.entity.Payment;
import com.example.schoolmanagement.entity.Student;
import com.example.schoolmanagement.entity.Teacher;
import com.example.schoolmanagement.enumconstants.AccountType;
import com.example.schoolmanagement.enumconstants.UserType;
import com.example.schoolmanagement.repository.PaymentRepository;
import com.example.schoolmanagement.repository.StudentRepository;
import com.example.schoolmanagement.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class SchedulerService {


    private static final Logger log = LoggerFactory.getLogger(SchedulerService.class);


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PaymentRepository paymentRepository;


//    @Scheduled(cron = "@monthly")
//    @Scheduled(cron = "0 0 12 1 * *")
    @Scheduled(cron = "0 */5 * ? * *")
    public void processPayment() {
        log.debug("processing payment in every 5 minutes");
        LocalDateTime presentDate = LocalDateTime.now();
        List<Student> students = this.studentRepository.findAll();
        List<Teacher> teachers=this.teacherRepository.findAll();
        students.forEach(student -> {
            String studentMonth = student.getCreatedDate().toLocalDate().getMonth().toString();
            if (!studentMonth.equals(presentDate.toLocalDate().getMonth().toString())) {
                double studentWorkedDays = student.getCreatedDate().getDayOfYear();
                double presentDayOfYear = presentDate.getDayOfYear();
                double remainingDays = 0.0, amount = 0.0;
                if (presentDayOfYear > studentWorkedDays) {
                    remainingDays = presentDayOfYear - studentWorkedDays;
                }
                if (remainingDays > 30) {
                    amount = amount + 10000.0;
                } else if (remainingDays > 60) {
                    amount = amount + 20000.0;
                }
                if (amount > 0.0) {
                    this.mapPayment(amount, student,new Teacher());
                }
            }
        });
        teachers.forEach(teacher -> {
            String teacherMonth = teacher.getCreatedDate().toLocalDate().getMonth().toString();
            if (!teacherMonth.equals(presentDate.toLocalDate().getMonth().toString())) {
                double teacherWorkingDays = teacher.getCreatedDate().getDayOfYear();
                double presentDayOfYear = presentDate.getDayOfYear();
                double remainingDays = 0.0, amount = 0.0;
                if (presentDayOfYear > teacherWorkingDays) {
                    remainingDays = presentDayOfYear - teacherWorkingDays;
                }
                if (remainingDays > 30) {
                    amount = Double.parseDouble(teacher.getSalary()) + 30000.0;
                } else if (remainingDays > 60) {
                    amount = Double.parseDouble(teacher.getSalary()) + 60000.0;
                }
                if (amount > 0.0) {
                    this.mapPayment(amount, new Student(),teacher);
                }
            }
        });
    }

    private void mapPayment(double amount, Student student, Teacher teacher) {
        Payment payment = new Payment();
        payment.setCreatedDate(LocalDateTime.now());
        payment.setUpdatedDate(LocalDateTime.now());
        payment.setAmount(amount);
        payment.setDeleted(false);
        payment.setStatus(true);
        if(Objects.nonNull(student) && Objects.nonNull(student.getId())){
            payment.setStudent(student);
            payment.setFirstName(student.getFirstName());
            payment.setLastName(student.getLastName());
            payment.setAccountType(AccountType.CREDIT);
            payment.setRole(UserType.ROLE_STUDENT);
        } else if(Objects.nonNull(teacher) && Objects.nonNull(teacher.getId())){
            payment.setTeacher(teacher);
            payment.setFirstName(teacher.getFirstName());
            payment.setLastName(teacher.getLastName());
            payment.setAccountType(AccountType.DEBIT);
            payment.setRole(UserType.ROLE_TEACHER);
        }
        this.paymentRepository.save(payment);
    }
}

