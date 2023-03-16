package com.lesnoy.oidcservice.auth.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.lesnoy.oidcservice.auth.JWTClaims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleJwtService {

    @Value("${authentication.google-client-id}")
    private String secret;

    public GoogleIdToken convertToGoogleToken(String token) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                .Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(secret))
                .build();
        return verifier.verify(token);
    }

    public String extractEmail(GoogleIdToken googleToken) {
        return extractClaim(googleToken, JWTClaims.EMAIL);
    }

    public String extractClaim(GoogleIdToken googleToken, JWTClaims claim) {
        GoogleIdToken.Payload payload = googleToken.getPayload();
        return payload.get(claim.getClaim()).toString();
    }
}
