package com.lesnoy.oidcservice.controller;

import com.lesnoy.oidcservice.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @GetMapping
    public ResponseEntity<String> admin(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok("Welcome home, " + user.getName());
    }

}
