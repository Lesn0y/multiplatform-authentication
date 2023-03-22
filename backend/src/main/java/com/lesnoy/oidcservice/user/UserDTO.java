package com.lesnoy.oidcservice.user;

public record UserDTO(
        int id,
        String username,
        String name,
        String icon
) {
}
