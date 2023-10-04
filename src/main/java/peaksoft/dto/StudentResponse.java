package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String fullName;
    private int age;
    private String email;
    private LocalDate createdDate;
    private LocalDate graduationDate;
    private boolean isBlock;
    private String phoneNumber;


    public StudentResponse(Long id, String fullName, int age, String email, LocalDate createdDate, LocalDate graduationDate, boolean isBlock, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.createdDate = createdDate;
        this.graduationDate = graduationDate;
        this.isBlock = isBlock;
        this.phoneNumber = phoneNumber;
    }
}
