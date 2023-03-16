package com.lesnoy.oidcservice.controller;

import com.lesnoy.oidcservice.auth.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome home");
    }
}
