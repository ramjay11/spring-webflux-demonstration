package com.ramjava.spring.webflux.demonstration.controller;

import com.ramjava.spring.webflux.demonstration.entity.Student;
import com.ramjava.spring.webflux.demonstration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/synchronous")
    public List<Student> students() {
        return service.getStudents();
    }

    // in reactive programs, we need to send data as an event stream
    @GetMapping(value = "/asynchronous", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> studentList() {
        return service.getStudentList();
    }
}
