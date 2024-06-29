package com.insta.Instagram_clone.restcontroller;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.service.*;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    // private String generateId() {
    // // Generate a UUID (Universally Unique Identifier)
    // return UUID.randomUUID().toString();
    // }
    @PostMapping("/signup")
    public ResponseEntity<Account> signUp(@RequestBody AccountDto accountDto) {
        // if (account.getId() == null || account.getId().isEmpty()) {
        // // If ID is not provided, generate a new ID (e.g., using UUID)
        // String generatedId = generateId(); // Implement this method to generate an ID
        // account.setId(generatedId);
        // }

        Account newAccount = authenticationService.signUp(accountDto);
        return ResponseEntity.ok(newAccount);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String userId = credentials.get("id");
        String username = credentials.get("username");
        String password = credentials.get("password");

        Account account = authenticationService.login(userId, username, password);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

}
