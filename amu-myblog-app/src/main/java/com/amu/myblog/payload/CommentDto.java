package com.amu.myblog.payload;

import lombok.Data;

/**
 * @Created 2022/06/21
 * @Author Amu
 */
@Data
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
}
