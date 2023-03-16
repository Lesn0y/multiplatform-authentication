package com.lesnoy.oidcservice.auth;

import com.lesnoy.oidcservice.auth.dto.LoginRequest;
import com.lesnoy.oidcservice.auth.dto.AuthResponse;
import com.lesnoy.oidcservice.auth.dto.RegisterRequest;
import com.lesnoy.oidcservice.user.Role;
import com.lesnoy.oidcservice.user.User;
import com.lesnoy.oidcservice.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder encoder;
    private final UserService service;
    private final AuthenticationManager manager;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .name(request.getName())
                .role(Role.USER)
                .build();
        service.save(user);
        return new AuthResponse(request.getUsername(), jwtService.generateToken(user));
    }

    public AuthResponse login(LoginRequest request) {
        System.out.println(encoder.encode(request.getPassword()));
        manager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        UserDetails user = service.loadUserByUsername(request.getUsername());

        var token = jwtService.generateToken(user);
        return new AuthResponse(user.getUsername(), token);
    }

}
