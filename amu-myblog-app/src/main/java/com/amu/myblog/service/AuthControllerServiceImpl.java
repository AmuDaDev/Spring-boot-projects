package com.amu.myblog.service;

import com.amu.myblog.exception.BlogAPIException;
import com.amu.myblog.model.Role;
import com.amu.myblog.model.User;
import com.amu.myblog.payload.LoginDto;
import com.amu.myblog.payload.SignUpDto;
import com.amu.myblog.repository.RoleRepository;
import com.amu.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @Created 2022/06/25
 * @Author Amu
 */
@Service
public class AuthControllerServiceImpl implements AuthControllerService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;

    public AuthControllerServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String signIn(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Successful logged in!";
    }

    @Override
    public String signUp(SignUpDto signUpDto) {
        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email is already taken!");
        }

        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);

        return "Successful signed up!";
    }
}
