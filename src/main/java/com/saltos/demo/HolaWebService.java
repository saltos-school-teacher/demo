package com.saltos.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
public class HolaWebService {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/greeting")
    public String greeting() {
        return "Hola the time is " + (new Date());
    }

    @GetMapping("/student")
    public Optional<StudentData> findStudent(@RequestParam(value = "id") Integer studentId) {
        Optional<StudentData> student = studentRepository.findById(studentId);
        return student;
    }

    @GetMapping("/create-student")
    public StudentData createStudent() {
        StudentData student = new StudentData(null, "Paul Saltos", 45);
        studentRepository.save(student);
        return student;
    }
}
