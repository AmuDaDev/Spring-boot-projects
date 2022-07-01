package com.amu.myblog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @Created 2022/06/18
 * @Author Amu
 */
@ApiModel(description = "Post information")
@Data
public class PostDto {
    @ApiModelProperty(value = "Blog post id")
    private Long id;
    @ApiModelProperty(value = "Blog post title",required = true)
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;
    @ApiModelProperty(value = "Blog post description",required = true)
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;
    @ApiModelProperty(value = "Blog post content",required = true)
    @NotEmpty
    private String content;
    @ApiModelProperty(value = "Blog post comments")
    private Set<CommentDto> comments;
}
