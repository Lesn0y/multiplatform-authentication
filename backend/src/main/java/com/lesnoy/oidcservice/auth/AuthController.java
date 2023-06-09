package com.lesnoy.oidcservice.auth;

import com.lesnoy.oidcservice.auth.dto.AuthResponse;
import com.lesnoy.oidcservice.auth.dto.LoginRequest;
import com.lesnoy.oidcservice.auth.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.authentication(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return new ResponseEntity<>(authService.registration(request), HttpStatus.CREATED);
    }
}
