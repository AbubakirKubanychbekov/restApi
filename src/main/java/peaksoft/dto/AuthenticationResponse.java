package peaksoft.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import peaksoft.enums.Role;

@Data
@Builder
@AllArgsConstructor
public record AuthenticationResponse(String token, String email, Role role){



}
