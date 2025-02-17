package az.portfoliomanagement.service.auth;

import az.portfoliomanagement.dto.auth.AuthenticationRequest;
import az.portfoliomanagement.dto.auth.AuthenticationResponse;
import az.portfoliomanagement.dto.auth.RegistrationRequest;
import az.portfoliomanagement.dto.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    UserResponse register(RegistrationRequest registrationRequest);

    AuthenticationResponse authenticate(AuthenticationRequest auth);
}
