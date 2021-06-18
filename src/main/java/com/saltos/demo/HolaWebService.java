package com.saltos.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
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

    @GetMapping("/list-students")
    public List<StudentData> findAllStudents() {
        List<StudentData> students = studentRepository.findAll();
        return students;
    }

    @GetMapping("/create-student")
    public StudentData createStudent(@RequestParam(value = "fullname") String fullname,
                                     @RequestParam(value = "age", defaultValue = "25") Integer age) {
        /*Integer age;
        if (ageString == null && ageString.equals("")) {
            age = 25;
        } else {
            age = Integer.valueOf(ageString);
        }*/
        StudentData student = new StudentData(null, fullname, age);
        studentRepository.save(student);
        return student;
    }
}
