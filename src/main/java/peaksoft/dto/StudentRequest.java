package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
//    private LocalDate createdDate;  // зависить от фигма
    private LocalDate graduationDate;
//    private boolean isBlock;
}
