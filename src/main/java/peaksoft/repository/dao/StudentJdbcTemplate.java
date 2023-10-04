package peaksoft.repository.dao;

import peaksoft.dto.StudentResponse;

import java.util.List;
import java.util.Optional;

public interface StudentJdbcTemplate {

    List<StudentResponse> getAllStudents();

   Optional<StudentResponse> getStudentById(Long id);


}
