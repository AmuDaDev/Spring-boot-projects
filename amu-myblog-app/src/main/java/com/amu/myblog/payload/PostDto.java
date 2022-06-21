package com.amu.myblog.payload;

import lombok.Data;

import java.util.Set;

/**
 * @Created 2022/06/18
 * @Author Amu
 */
@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;
}
