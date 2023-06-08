package com.example.schoolmanagement.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentMessageResource {

    private final Logger log = LoggerFactory.getLogger(StudentMessageResource.class);


    @GetMapping("/teacher/message")
    @KafkaListener(topics = "teacher_channel",groupId = "teacher_channel_group")
    public void teacherMessage(@RequestParam(value = "message") String message){
        log.info("message is received"+message);
    }
}
