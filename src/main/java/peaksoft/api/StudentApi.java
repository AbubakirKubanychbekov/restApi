package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.PaginationResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.StudentRequest;
import peaksoft.dto.StudentResponse;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "StudentApi")
public class StudentApi {

    private final StudentService studentService;

    @PermitAll
    @GetMapping
    public List<StudentResponse> getAllStudents(){
        return studentService.getAllStudent();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public StudentResponse saveStudent(@RequestBody @Valid StudentRequest studentRequest) {
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

    @GetMapping("/pagination")
    public PaginationResponse paginationResponse(@RequestParam int currentPage,
                                          @RequestParam int pageSize){
        return studentService.getAllPagination(currentPage,pageSize);

    }

}
