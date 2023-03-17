package com.lesnoy.oidcservice.auth.dto;

public record AuthResponse(
        String username,
        String token) {}
