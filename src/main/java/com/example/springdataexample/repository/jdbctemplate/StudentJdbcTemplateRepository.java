package com.example.springdataexample.repository.jdbctemplate;

import com.example.springdataexample.domain.Student;
import com.example.springdataexample.domain.StudentRowMapper;
import com.example.springdataexample.repository.StudentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// JDBC API 직접사용하는 것 보다 중복되는 부분이 많이 제거됨
// JdbcTemplate도 JDBC와 마찬가지로 데이터를 다루는(저장/조회/수정/삭제) 것을 도와주는 API
// JdbcTemplate은 SQL Mapper의 일종 - SQL을 직접 작성하고 Object의 필드를 매핑하여 데이터를 객체화
@Repository
public class StudentJdbcTemplateRepository implements StudentRepository {
    private JdbcTemplate jdbcTemplate;

    public StudentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int countOfStudents() {
        return jdbcTemplate.queryForObject("select count(*) from students", Integer.class);
    }

    @Override
    public List<String> selectListOfStudentName() {
        return jdbcTemplate.query("select name from students", (rs, rowNum) -> rs.getString("name"));
    }

    @Override
    public int insertStudent(Map<String, Object> params) {
        String name = (String) params.get("name");
        int age = (int) params.get("age");
        String address = (String) params.get("address");

        return jdbcTemplate.update(
                "INSERT INTO students (name, age, address) VALUES (?,?,?)", name, age, address);
    }

    @Override
    public int insertStudent(Student student) {
        return jdbcTemplate.update(
                "INSERT INTO students (name, age, address) VALUES(?, ?, ?)",
                student.getName(), student.getAge(), student.getAddress());
    }

    @Override
    public List<Student> findAll() {
        List<Student> list = jdbcTemplate.query("SELECT * FROM students", new StudentRowMapper());

        return jdbcTemplate.query(
                "SELECT * FROM students",
                (rs, rowNum) ->
                        new Student(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getInt("age"),
                                rs.getString("address")
                        )
        );
    }

    @Override
    public List<Student> findStudents(String name) {
        return jdbcTemplate.query(
                "select * from students where name like ?",
                new Object[]{"%" + name + "%"},
                (rs, rowNum) ->
                        new Student(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getInt("age"),
                                rs.getString("address")
                        )
        );
    }

    @Override
    public Optional<Student> selectStudentById(Integer studentId) {
        return jdbcTemplate.queryForObject(
                "select * from students where id = ?",
                new Object[]{studentId},
                (rs, rowNum) ->
                        Optional.of(new Student(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getInt("age"),
                                rs.getString("address")
                        ))
        );
    }
}
