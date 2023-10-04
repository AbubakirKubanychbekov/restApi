package peaksoft.dto;

import lombok.*;

@Builder
public record SignInRequest(
        String email,
        String password
) {

    public SignInRequest {
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }
}
