package com.classeye.authservice.controller;

import com.classeye.authservice.service.impl.CognitoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sejja
 **/
@RestController
@RequestMapping()
public class AuthenticationController {

    private final CognitoService cognitoService;
    @Autowired
    public AuthenticationController(CognitoService cognitoService) {
        this.cognitoService = cognitoService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestParam String email,
                                             @RequestParam String groupName) {
        cognitoService.createUser(email, groupName);
        return ResponseEntity.ok("User created successfully and email sent.");
    }
}