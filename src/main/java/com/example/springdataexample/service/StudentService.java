package com.example.springdataexample.service;

import com.example.springdataexample.domain.Student;
import com.example.springdataexample.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {
//    @Autowired
//    @Qualifier("studentNamedParameterJdbcRepository")
//    private StudentRepository studentRepository;
    @Autowired
    @Qualifier("studentJdbcTemplateRepository")
    private StudentRepository studentRepository;

    public Integer countOfStudent() {
        return studentRepository.countOfStudents();
    }

    public List<String> getListOfStudentName() {
        return studentRepository.selectListOfStudentName();
    }

    public void registStudent(Map<String, Object> params) {
        studentRepository.insertStudent(params);
    }

    public void registStudent(Student student) {
        studentRepository.insertStudent(student);
    }

    public List<Student> getListOfStudent() {
        return studentRepository.findAll();
    }

    public List<Student> findStudents(String name) {
        return studentRepository.findStudents(name);
    }

    public Optional<Student> findStudent(Integer studentId) {
        return studentRepository.selectStudentById(studentId);
    }
}
