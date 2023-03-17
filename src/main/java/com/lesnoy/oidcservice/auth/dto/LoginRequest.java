package com.lesnoy.oidcservice.auth.dto;

public record LoginRequest(
        String username,
        String password) {}
