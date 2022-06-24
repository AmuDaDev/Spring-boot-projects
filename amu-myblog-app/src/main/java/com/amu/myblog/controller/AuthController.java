package com.amu.myblog.controller;

import com.amu.myblog.payload.LoginDto;
import com.amu.myblog.payload.SignUpDto;
import com.amu.myblog.service.AuthControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created 2022/06/24
 * @Author Amu
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthControllerService authControllerService;

    @PostMapping("/signin")
    public ResponseEntity<String> registerUser(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(authControllerService.signIn(loginDto), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDto signUpDto){
        return new ResponseEntity<>(authControllerService.signUp(signUpDto), HttpStatus.OK);
    }
}
