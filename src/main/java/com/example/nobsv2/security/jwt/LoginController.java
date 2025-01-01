package com.example.nobsv2.security.jwt;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.nobsv2.security.CustomUser;

@RestController
public class LoginController {
    
    private final AuthenticationManager manager;

    public LoginController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CustomUser user) {
        // this token is different than a jwt
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            user.getUsername(),
            user.getPassword()
        );

        // this will fault if credentials not valid
        Authentication authentication = manager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = JwtUtil.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(jwtToken);
    }
}
