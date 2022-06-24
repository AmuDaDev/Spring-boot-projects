package com.amu.myblog.service;

import com.amu.myblog.payload.LoginDto;
import com.amu.myblog.payload.SignUpDto;

/**
 * @Created 2022/06/24
 * @Author Amu
 */
public interface AuthControllerService {
    String signIn(LoginDto loginDto);
    String signUp(SignUpDto signUpDto);
}
