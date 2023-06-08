package com.example.schoolmanagement.api;

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
    private KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping("/send/message")
    public ResponseEntity<String> sendMessage(@RequestParam(value = "message") String message) {
        kafkaTemplate.send("teacher_channel", message);
        return new ResponseEntity<>("message", HttpStatus.OK);
    }
}
