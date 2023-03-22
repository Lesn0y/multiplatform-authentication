package com.lesnoy.oidcservice.controller;

import com.lesnoy.oidcservice.user.User;
import com.lesnoy.oidcservice.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping
    public ResponseEntity<UserDTO> user(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getImg()
        ));
    }
}
