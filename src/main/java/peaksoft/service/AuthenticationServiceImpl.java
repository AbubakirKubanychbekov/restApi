package peaksoft.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dto.AuthenticationResponse;
import peaksoft.dto.SignInRequest;
import peaksoft.dto.SignUpRequest;
import peaksoft.entity.User;
import peaksoft.repository.UserRepo;
import peaksoft.security.JwtService;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        System.out.println("test1");
        if (userRepo.existsByEmail(signUpRequest.email())){
           throw new EntityExistsException(String.format("User with email: %s already exists!",signUpRequest.email()));
       }
      User user = User.builder()
                .firstName(signUpRequest.firstName())
                .lastName(signUpRequest.lastName())
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .role(signUpRequest.role())
                .build();
       userRepo.save(user);
        System.out.println("test2");

       String jwtToken= jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = userRepo.getUserByEmail(signInRequest.email()).orElseThrow(() ->
                new EntityNotFoundException("User with email: " + signInRequest.email() + " not found"));

         if (signInRequest.email().isBlank()){
             throw new BadCredentialsException("Email is blank");
         }
         if (!passwordEncoder.matches(signInRequest.password(), user.getPassword())){
             throw new BadCredentialsException("Wrong password!");
         }
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
