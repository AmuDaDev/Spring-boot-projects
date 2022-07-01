package com.amu.myblog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Created 2022/06/21
 * @Author Amu
 */
@ApiModel(description = "Comment information")
@Data
public class CommentDto {
    @ApiModelProperty(value = "Comment id")
    private long id;
    @ApiModelProperty(value = "Comment name",required = true)
    @NotEmpty(message = "Name should not be null or empty")
    private String name;
    @ApiModelProperty(value = "Comment email",required = true)
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;
    @ApiModelProperty(value = "Comment body",required = true)
    @NotEmpty
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}
