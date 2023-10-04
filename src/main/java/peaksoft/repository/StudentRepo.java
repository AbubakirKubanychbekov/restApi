package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.StudentResponse;
import peaksoft.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {


    @Query("select new peaksoft.dto.StudentResponse(s.id,concat(s.firstName,s.lastName) ,s.age,s.email,s.createdDate,s.graduationDate,s.isBlock,s.phoneNumber)from Student s")
    Page<StudentResponse> getAllStudents(Pageable pageable);
}
