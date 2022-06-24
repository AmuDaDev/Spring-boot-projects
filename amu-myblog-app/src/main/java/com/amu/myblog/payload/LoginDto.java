package com.amu.myblog.payload;

import lombok.Data;

/**
 * @Created 2022/06/24
 * @Author Amu
 */
@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
