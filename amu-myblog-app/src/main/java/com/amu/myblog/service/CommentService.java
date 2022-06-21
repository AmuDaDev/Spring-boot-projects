package com.amu.myblog.service;

import com.amu.myblog.payload.CommentDto;

import java.util.List;

/**
 * @Created 2022/06/21
 * @Author Amu
 */
public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);
    CommentDto getCommentById(Long postId, Long commentId);
    CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest);
    void deleteComment(Long postId, Long commentId);
}
