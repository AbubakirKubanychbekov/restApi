package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.StudentRequest;
import peaksoft.dto.StudentResponse;
import peaksoft.entity.Student;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentApi {

    private final StudentService studentService;

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudent();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
       return studentService.saveStudent(studentRequest);
    }

    @PermitAll
    @GetMapping("/{studentId}")
    public StudentResponse getById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @PutMapping("/{studentId}")
    SimpleResponse updateStudent(@PathVariable Long studentId,
                                 @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(studentId,studentRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @DeleteMapping("/{studentId}")
    SimpleResponse deleteStudent(@PathVariable Long studentId) {
        return studentService.deleteStudent(studentId);
    }

}
