package peaksoft.service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.StudentRequest;
import peaksoft.dto.StudentResponse;
import peaksoft.entity.Student;
import peaksoft.repository.StudentRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional
@RequiredArgsConstructor
public class
StudentServiceImp implements StudentService{

    private final StudentRepo studentRepo;
    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        Student student= new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setAge(studentRequest.getAge());
        student.setEmail(studentRequest.getEmail());
        student.setCreatedDate(LocalDate.now());
        student.setCreatedDate(studentRequest.getGraduationDate());
        student.setBlock(true);
       studentRepo.save(student);
       return new StudentResponse(
               student.getId(),
               student.getFirstName()+student.getLastName(),
               student.getAge(),
               student.getEmail(),
               student.getCreatedDate(),
               student.getGraduationDate(),
               student.isBlock()
       );
    }

    @Override
    public List<StudentResponse> getAllStudent() {
        return studentRepo.findAllStudents();
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        return studentRepo.findStudentById(id).orElseThrow(()->
                new NoSuchElementException("Student with id: "+id+" not found"));

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

}
