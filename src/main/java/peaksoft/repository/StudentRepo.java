package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.StudentResponse;
import peaksoft.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student,Long> {

    @Query("select new peaksoft.dto.StudentResponse(s.id, concat(s.firstName,' ',s.lastName),s.age,s.email,s.createdDate,s.graduationDate,s.isBlock)from Student s")
    List<StudentResponse> findAllStudents();

    Optional<StudentResponse> findStudentById(Long id);



}
