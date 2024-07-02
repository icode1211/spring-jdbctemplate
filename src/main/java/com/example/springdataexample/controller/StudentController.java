package com.example.springdataexample.controller;

import com.example.springdataexample.domain.Student;
import com.example.springdataexample.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/count")
    public Integer countOfStudent() {
        return studentService.countOfStudent();
    }

    @GetMapping("/student/name/list")
    public List<String> getListOfStudentName() {
        return studentService.getListOfStudentName();
    }

    /**
     * curl -X PUT "http://localhost:8080/student/regist" -d '{ "name":"김영수", "age":"28"}'
     */
    @PutMapping("/student/regist")
    public void registStudent(@RequestParam Map<String, Object> params) {
        studentService.registStudent(params);
    }

    @PutMapping("/student/regist2")
    public void registStudent(@RequestBody Student student) {
        studentService.registStudent(student);
    }

    @GetMapping("/student/list")
    public List<Student> getListOfStudent() {
        return studentService.getListOfStudent();
    }

    @GetMapping("/student/search")
    public List<Student> searchStudent(@RequestParam String name) {
        return studentService.findStudents(name);
    }

    @GetMapping("/student/search/{studentId}")
    public Optional<Student> getStudent(@PathVariable Integer studentId) {
        return studentService.findStudent(studentId);
    }
}
