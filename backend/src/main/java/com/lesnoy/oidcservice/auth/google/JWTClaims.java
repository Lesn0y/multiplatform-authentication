package com.lesnoy.oidcservice.auth.google;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JWTClaims {

    ISSUER("iss"),
    NOT_BEFORE("nbf"),
    AUDIENCE("aud"),
    SUBJECT("sub"),
    EMAIL("email"),
    FULL_NAME("name"),
    FIRST_NAME("given_name"),
    PICTURE("picture"),
    ISSUED_AT("iat"),
    EXPIRE_TIME("exp"),
    JWT_ID("jti");

    private final String claim;
}
