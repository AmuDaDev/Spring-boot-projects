package com.amu.myblog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Created 2022/06/24
 * @Author Amu
 */
@ApiModel(description = "Sign-up information")
@Data
public class SignUpDto {
    @ApiModelProperty(value = "User's name")
    @NotEmpty(message = "Name should not be null or empty")
    private String name;
    @ApiModelProperty(value = "User's Username")
    @NotEmpty(message = "Username should not be null or empty")
    @Size(min = 2, message = "Username should have at least 2 characters")
    private String username;
    @ApiModelProperty(value = "User's email")
    @Email
    @NotEmpty(message = "Email should not be null or empty")
    private String email;
    @ApiModelProperty(value = "User's password")
    @NotEmpty(message = "Password should not be null or empty")
    private String password;
}
