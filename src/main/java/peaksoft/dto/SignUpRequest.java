package peaksoft.dto;

import lombok.Builder;
import peaksoft.enums.Role;

@Builder
public record SignUpRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        Role role){

    public SignUpRequest {
    }

    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public String lastName() {
        return lastName;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public Role role() {
        return role;
    }
}
