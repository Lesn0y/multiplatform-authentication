package com.lesnoy.oidcservice.auth;

import com.lesnoy.oidcservice.auth.dto.LoginRequest;
import com.lesnoy.oidcservice.auth.dto.AuthResponse;
import com.lesnoy.oidcservice.auth.dto.RegisterRequest;
import com.lesnoy.oidcservice.auth.custom.NativeJwtService;
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
public class AuthServiceImpl {

    private final PasswordEncoder encoder;
    private final UserService service;
    private final AuthenticationManager manager;
    private final NativeJwtService nativeJwtService;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .name(request.getName())
                .build();
        service.save(user);
        return new AuthResponse();
    }

    public AuthResponse login(LoginRequest request) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        UserDetails user = service.loadUserByUsername(request.getUsername());

        var token = nativeJwtService.generateToken(user);
        return new AuthResponse(user.getUsername(), token);
    }

}
