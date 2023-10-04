package peaksoft.repository.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import peaksoft.dto.StudentResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentJdbcTemplateImpl implements StudentJdbcTemplate{

    private final JdbcTemplate jdbcTemplate;



    private  StudentResponse rowMapper(ResultSet rs,int rowName) throws SQLException {
        return   new StudentResponse(
                rs.getLong("id"),
                rs.getString("fullName"),
                rs.getInt("age"),
                rs.getString("email"),
                LocalDate.parse(rs.getString("createdDate")),
                LocalDate.parse(rs.getString("graduationDate")),
                rs.getBoolean("isBlock"),
                rs.getString("phoneNumber"));
    }



    @Override
    public List<StudentResponse> getAllStudents() {
        String sql= """
                select id,
                concat (first_name,' ',last_name) as fullName,
                age as age,
                email as email,
                created_date as createdDate,
                graduation_date as graduationDate,
                isBlock as isBlock,
                phone_Number as phoneNumber
                from students
                """;
        return jdbcTemplate.query(sql, this::rowMapper);

    }

    @Override
    public Optional<StudentResponse> getStudentById(Long id) {
        String sql= """
                select id,
                concat (first_name,' ',last_name) as fullName,
                age as age,
                email as email,
                created_date as createdDate,
                graduation_date as graduationDate,
                isBlock as isBlock,
                phone_Number as phoneNumber
                from students where id=?
                """;
        return jdbcTemplate.query(sql,this::rowMapper,id).stream().findFirst();
    }


}
