package com.lesnoy.oidcservice.auth.dto;

public record RegisterRequest(
        String username,
        String password,
        String name) {}
