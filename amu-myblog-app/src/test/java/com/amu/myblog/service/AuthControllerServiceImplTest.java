package com.amu.myblog.service;

import com.amu.myblog.exception.BlogAPIException;
import com.amu.myblog.model.Role;
import com.amu.myblog.model.User;
import com.amu.myblog.payload.LoginDto;
import com.amu.myblog.payload.SignUpDto;
import com.amu.myblog.repository.RoleRepository;
import com.amu.myblog.repository.UserRepository;
import com.amu.myblog.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @Created 2022/07/09
 * @Author Amu
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthControllerServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenProvider tokenProvider;
    @InjectMocks
    private AuthControllerServiceImpl authControllerService;

    @Test
    void signIn() {
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(new UsernamePasswordAuthenticationToken(
                "amu@gmail.com","password",mapRolesToAuthorities(getRole())
        ));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        when(tokenProvider.generateToken(any(Authentication.class))).thenReturn("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbXVAZ21haWwuY29tIiwiaWF0IjoxNjU3Mjk1NzkwLCJleHAiOjE2NTc5MDA1OTB9.Jtx8Xkx_n6C3piJ_cd9A-5rE7ej1DvYC2Z04fJStCRCRb1oNG7UdFWo6z-PQQ2Xp7ZO1PXcNEbHhb7whoYVK5w");
        String token = authControllerService.signIn(getLoginDto());
        assertThat(token).isNotNull();
    }

    @Test
    void signUp() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("eyJhbGciOiJIUzUxMiJ900");
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(new Role()));
        when(userRepository.save(any(User.class))).thenReturn(new User());
        String response = authControllerService.signUp(getSignUpDto());
        assertThat(response).isEqualTo("Successful signed up!");
    }

    @Test
    void signUp_Username_BlogAPIException() {
        assertThatThrownBy(() -> {
            when(userRepository.existsByUsername(anyString())).thenReturn(true);
            authControllerService.signUp(getSignUpDto());
        }).isInstanceOf(BlogAPIException.class)
                .hasMessage("Username is already taken!");
    }

    @Test
    void signUp_Email_BlogAPIException() {
        assertThatThrownBy(() -> {
            when(userRepository.existsByEmail(anyString())).thenReturn(true);
            authControllerService.signUp(getSignUpDto());
        }).isInstanceOf(BlogAPIException.class)
                .hasMessage("Email is already taken!");
    }

    private SignUpDto getSignUpDto(){
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setName("Amu");
        signUpDto.setUsername("amu");
        signUpDto.setEmail("amu@gmail.com");
        signUpDto.setPassword("password");
        return signUpDto;
    }

    private LoginDto getLoginDto(){
        LoginDto loginDto = new LoginDto();
        loginDto.setUsernameOrEmail("amu@gmail.com");
        loginDto.setPassword("password");
        return loginDto;
    }

    private Set<Role> getRole(){
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        roles.add(role);
        return roles;
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}