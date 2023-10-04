package peaksoft.service;

import peaksoft.dto.PaginationResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.StudentRequest;
import peaksoft.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse saveStudent(StudentRequest studentRequest);

    List<StudentResponse>getAllStudent();

    StudentResponse getStudentById(Long id);

    SimpleResponse updateStudent(Long id,StudentRequest studentRequest);

    SimpleResponse deleteStudent(Long id);


    PaginationResponse getAllPagination(int currentPage, int pageSize);
}
