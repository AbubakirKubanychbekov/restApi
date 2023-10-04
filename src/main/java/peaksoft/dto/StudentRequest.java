package peaksoft.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import peaksoft.validation.PhoneNumberValidation;

import java.time.LocalDate;

@Getter
@Setter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private int age;

    @Email(message = "Должен все имеющие знак")
    private String email;
    private LocalDate graduationDate;

    @PhoneNumberValidation
    private String phoneNumber;


    private LocalDate createdDate;  // зависить от фигма
//    private boolean isBlock;
}
