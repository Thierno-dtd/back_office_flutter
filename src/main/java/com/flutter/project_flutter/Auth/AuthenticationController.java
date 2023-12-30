package com.flutter.project_flutter.Auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.flutter.project_flutter.constants.utils.APP_ROOT;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(APP_ROOT+"/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @PostMapping(value = "/registerUser")
    public ResponseEntity<AuthenticationResponse> registerUer(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping(value = "/registerAdmin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.registerAdmin(request));
    }
    @PostMapping(value = "/registerPartenaire")
    public ResponseEntity<AuthenticationResponse> registerPartenaire(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.registerPartenaire(request));
    }
}