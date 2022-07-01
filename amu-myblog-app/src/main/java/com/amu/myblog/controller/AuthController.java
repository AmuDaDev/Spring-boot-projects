package com.amu.myblog.controller;

import com.amu.myblog.payload.JWTAuthResponse;
import com.amu.myblog.payload.LoginDto;
import com.amu.myblog.payload.SignUpDto;
import com.amu.myblog.service.AuthControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import javax.validation.Valid;

/**
 * @Created 2022/06/24
 * @Author Amu
 */
@Api(value = "Auth controller exposes sign-in and sign-up APIs")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthControllerService authControllerService;

    public AuthController(AuthControllerService authControllerService) {
        this.authControllerService = authControllerService;
    }

    @ApiOperation(value = "API to Sign-in user to Blog app")
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> signInUser(@Valid @RequestBody LoginDto loginDto){
        String token = authControllerService.signIn(loginDto);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @ApiOperation(value = "API to Sign-up user to Blog app")
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpDto signUpDto){
        return new ResponseEntity<>(authControllerService.signUp(signUpDto), HttpStatus.OK);
    }
}
