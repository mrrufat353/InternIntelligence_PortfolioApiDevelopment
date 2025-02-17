package az.portfoliomanagement.controller;

import az.portfoliomanagement.dto.auth.AuthenticationRequest;
import az.portfoliomanagement.dto.auth.AuthenticationResponse;
import az.portfoliomanagement.dto.auth.RegistrationRequest;
import az.portfoliomanagement.dto.response.UserResponse;
import az.portfoliomanagement.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return new ResponseEntity<>(authenticationService.register(registrationRequest), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest auth) {
        return new ResponseEntity<>(authenticationService.authenticate(auth), HttpStatus.OK);
    }

}
