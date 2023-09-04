package peaksoft.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {  // TODO STUDENT MODEL
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_gen"
    )
    @SequenceGenerator(
            name = "user_gen",
            sequenceName = "user_seq",
            allocationSize = 1
    )

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private LocalDate createdDate;
    private LocalDate graduationDate;
    private boolean isBlock;


}
