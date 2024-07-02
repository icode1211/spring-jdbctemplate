package com.example.springdataexample.repository;

import com.example.springdataexample.domain.Student;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentRepository {
    int countOfStudents();
    List<String> selectListOfStudentName();

    int insertStudent(Map<String, Object> params);
    int insertStudent(Student student);

    List<Student> findAll();
    List<Student> findStudents(String name);

    Optional<Student> selectStudentById(Integer studentId);
}
