package peaksoft.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dto.PaginationResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.StudentRequest;
import peaksoft.dto.StudentResponse;
import peaksoft.entity.Student;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.StudentRepo;
import peaksoft.repository.dao.StudentJdbcTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j // Simple login facade for java
public class StudentServiceImp implements StudentService{

    private final StudentRepo studentRepo;
    private final StudentJdbcTemplate studentJdbcTemplate;

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        Student student= new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setAge(studentRequest.getAge());
        student.setEmail(studentRequest.getEmail());
        student.setCreatedDate(LocalDate.now());
        student.setCreatedDate(studentRequest.getGraduationDate());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setBlock(false);

       studentRepo.save(student);
       log.info("Student successfully saved.");
       return new StudentResponse(
               student.getId(),
               student.getFirstName(),
               student.getAge(),
               student.getEmail(),
               student.getCreatedDate(),
               student.getGraduationDate(),
               student.isBlock(),
               student.getPhoneNumber()
       );
    }

    @Override
    public List<StudentResponse> getAllStudent() {
        return studentJdbcTemplate.getAllStudents();
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        return studentJdbcTemplate.getStudentById(id)
                .orElseThrow( ()-> {
                    String message = "Student with id: " + id + " not found";
                    log.error(message);
                    return new NotFoundException(message);
                });
    }



    @Override
    public SimpleResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student student = studentRepo.findById(id).orElseThrow(() ->
                new NoSuchElementException("Student with id: " + id + " not found!"));
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setAge(studentRequest.getAge());
        student.setEmail(studentRequest.getEmail());
        student.setCreatedDate(LocalDate.now());
        student.setGraduationDate(studentRequest.getGraduationDate());
        student.setBlock(false);
        return new SimpleResponse(
                HttpStatus.OK,
                "Successfully student is updated"
        );
    }

    @Override
    public SimpleResponse deleteStudent(Long id) {
        if (!studentRepo.existsById(id)) {
            throw new NullPointerException(
                    "student with id: " + id + "  not found");
        }
        studentRepo.deleteById(id);
        return new SimpleResponse(
                HttpStatus.OK,
                "Student with id: "+id+" is deleted"
        );
    }



    @Override
    public PaginationResponse getAllPagination(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage-1,pageSize);
        Page<StudentResponse> students = studentRepo.getAllStudents(pageable);
        return PaginationResponse.builder()
                .students(students.getContent())
                .currentPage(students.getNumber()+1)
                .pageSize(students.getTotalPages())
                .build();
    }

}
