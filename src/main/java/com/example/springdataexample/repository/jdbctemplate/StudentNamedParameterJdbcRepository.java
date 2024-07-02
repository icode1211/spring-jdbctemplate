package com.example.springdataexample.repository.jdbctemplate;

import com.example.springdataexample.domain.Student;
import com.example.springdataexample.repository.StudentRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// JdbcTemplate 에서 ? 을 이용하여 데이터 처리를 했던 방식에서 개선
// ? 대신에 :변수명 을 이용하여 처리함으로써 파라미터의 순서에 강제를 받지 않음.
// 파라미터에 이름을 지어서 쿼리를 실행할 수 있음.
@Repository("studentNamedParameterJdbcRepository")
public class StudentNamedParameterJdbcRepository implements StudentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StudentNamedParameterJdbcRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int countOfStudents() {
        return 0;
    }

    @Override
    public List<String> selectListOfStudentName() {
        return null;
    }

    @Override
    public int insertStudent(Map<String, Object> params) {
        return 0;
    }

    @Override
    public int insertStudent(Student student) {
        return 0;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public List<Student> findStudents(String name) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource().addValue("name", name);
        return jdbcTemplate.query("SELECT * FROM students WHERE name = :name",
                mapSqlParameterSource,
                (rs, rowNum) -> new Student(rs.getLong("id"), rs.getString("name"),
                        rs.getInt("age"), rs.getString("address")));
    }

    @Override
    public Optional<Student> selectStudentById(Integer studentId) {
        return jdbcTemplate.queryForObject(
                "select * from students where id = :id",
                new MapSqlParameterSource("id", studentId),
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
