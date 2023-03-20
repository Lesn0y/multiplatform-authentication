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

    public AuthResponse registration(RegisterRequest request) {
        User user = User.builder()
                .username(request.username())
                .password(encoder.encode(request.password()))
                .name(request.name())
                .role(Role.USER)
                .build();
        service.save(user);
        return new AuthResponse(jwtService.generateToken(user));
    }

    public AuthResponse authentication(LoginRequest request) {

        manager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        ));

        UserDetails user = service.loadUserByUsername(request.username());

        var token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

}
