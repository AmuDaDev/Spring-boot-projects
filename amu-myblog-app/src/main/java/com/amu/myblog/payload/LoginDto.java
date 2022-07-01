package com.amu.myblog.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Created 2022/06/24
 * @Author Amu
 */
@Api(value = "Login or Sign-in model information")
@Data
public class LoginDto {
    @ApiModelProperty(value = "Username or Email")
    @NotEmpty(message = "Username Or Email should not be null or empty")
    @Size(min = 2, message = "username Or Email should have at least 2 characters")
    private String usernameOrEmail;
    @ApiModelProperty(value = "User password")
    @NotEmpty(message = "Password should not be null or empty")
    private String password;
}