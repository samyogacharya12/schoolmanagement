package com.example.schoolmanagement.api;

import com.example.schoolmanagement.dto.TeacherDto;
import com.example.schoolmanagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TeacherMessageResource {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private TeacherService teacherService;


    @GetMapping("/send/message")
    public ResponseEntity<String> sendMessage(@RequestParam(value = "id") Long id)  {
            TeacherDto teacherDto = this.teacherService.findById(id);
            kafkaTemplate.send("teacher_channel", teacherDto.getPhoneNumber());
            return new ResponseEntity<>("message", HttpStatus.OK);
    }
}
