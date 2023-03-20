package com.lesnoy.oidcservice.auth.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.lesnoy.oidcservice.user.Role;
import com.lesnoy.oidcservice.user.User;
import com.lesnoy.oidcservice.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthUserService extends DefaultOAuth2UserService {

    private final GoogleService googleService;
    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        Map<String, Object> parameters = userRequest.getAdditionalParameters();
        try {
            GoogleIdToken googleToken = googleService.convertToGoogleToken(String.valueOf(parameters.get("id_token")));

            String username = googleService.extractEmail(googleToken);
            User user = userService.findUserByUsername(username);
            if (user == null) {
                String name = googleService.extractClaim(googleToken, JWTClaims.FIRST_NAME);
                String picture = googleService.extractClaim(googleToken, JWTClaims.PICTURE);
                return userService.save(User.builder()
                        .username(username)
                        .name(name)
                        .img(picture)
                        .role(Role.USER)
                        .build());
            }
            return user;
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
