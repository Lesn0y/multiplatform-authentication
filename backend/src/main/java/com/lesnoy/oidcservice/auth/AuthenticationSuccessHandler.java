package com.lesnoy.oidcservice.auth;

import com.lesnoy.oidcservice.user.Role;
import com.lesnoy.oidcservice.user.User;
import com.lesnoy.oidcservice.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserService userService;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        User user = userService.findUserByUsername(authentication.getName());
        String token = jwtService.generateToken(user);

        response.addHeader("Authorization", "Bearer " + token);

        redirectStrategy.sendRedirect(request, response, determineTargetUrl(user));
    }

    private String determineTargetUrl(User user) {
        Map<Role, String> roleTargetUrlMap = new HashMap<>();

        roleTargetUrlMap.put(Role.USER, "/api/v1/user");
        roleTargetUrlMap.put(Role.ADMIN, "/api/v1/admin");

        return roleTargetUrlMap.get(user.getRole());
    }
}
