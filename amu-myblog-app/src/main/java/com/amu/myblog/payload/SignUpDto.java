package com.amu.myblog.payload;

import lombok.Data;

/**
 * @Created 2022/06/24
 * @Author Amu
 */
@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
