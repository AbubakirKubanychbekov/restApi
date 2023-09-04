package peaksoft.service;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dto.AuthenticationResponse;
import peaksoft.dto.SignInRequest;
import peaksoft.dto.SignUpRequest;
import peaksoft.entity.User;
import peaksoft.repository.UserRepo;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepo userRepo;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
       if (userRepo.existsByEmail(signUpRequest.getEmail())){
           throw new EntityExistsException(String.format("User with email: %s already exists!",signUpRequest.getEmail()));
       }
      User user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .role(signUpRequest.getRole())
                .build();
       userRepo.save(user);

       // Todo token generate
       String jwtToken="";

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        return null;
    }
}
