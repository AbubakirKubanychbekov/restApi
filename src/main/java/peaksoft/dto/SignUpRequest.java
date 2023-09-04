package peaksoft.dto;

import lombok.*;
import peaksoft.enums.Role;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@Getter
@Setter
public record SignUpRequest(String firstName, String lastName,String email,String password,Role role){


}
