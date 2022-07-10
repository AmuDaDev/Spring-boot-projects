package com.amu.myblog.service;

import com.amu.myblog.exception.BlogAPIException;
import com.amu.myblog.exception.ResourceNotFoundException;
import com.amu.myblog.model.Comment;
import com.amu.myblog.model.Post;
import com.amu.myblog.payload.CommentDto;
import com.amu.myblog.repository.CommentRepository;
import com.amu.myblog.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @Created 2022/07/08
 * @Author Amu
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CommentServiceImplTest {
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void createComment() {
        when(mapper.map(getCommentDto(),Comment.class)).thenReturn(getComment());
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(getPost()));
        when(commentRepository.save(any(Comment.class))).thenReturn(getComment());
        when(mapper.map(getComment(),CommentDto.class)).thenReturn(getCommentDto());
        CommentDto commentDto = commentService.createComment(1l,getCommentDto());
        assertThat(commentDto.getName()).isEqualTo(getComment().getName());
    }

    @Test
    void createComment_ResourceNotFoundException() {
        assertThatThrownBy(() -> {
            when(postRepository.findById(anyLong())).thenReturn(Optional.empty());
            commentService.createComment(1l,getCommentDto());
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("not found with");
    }

    @Test
    void getCommentsByPostId() {
        List<Comment> comments = new ArrayList<>();
        comments.add(getComment());
        when(commentRepository.findByPostId(anyLong())).thenReturn(comments);
        List<CommentDto> retrievedComments = commentService.getCommentsByPostId(1l);
        assertThat(retrievedComments.size()).isEqualTo(1);
    }

    @Test
    void getCommentById() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(getPost()));
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(getComment()));
        when(mapper.map(getComment(),CommentDto.class)).thenReturn(getCommentDto());
        CommentDto commentDto = commentService.getCommentById(1l,1l);
        assertThat(commentDto.getName()).isEqualTo(getComment().getName());
    }

    @Test
    void getCommentById_BlogAPIException() {
        assertThatThrownBy(() -> {
            Post post = getPost();
            post.setId(2l);
            when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
            when(commentRepository.findById(anyLong())).thenReturn(Optional.of(getComment()));
            commentService.getCommentById(1l,1l);
        }).isInstanceOf(BlogAPIException.class)
                .hasMessage("Comment does not belong to post");
    }

    @Test
    void getCommentById_Post_ResourceNotFoundException() {
        assertThatThrownBy(() -> {
            when(postRepository.findById(anyLong())).thenReturn(Optional.empty());
            commentService.getCommentById(1l,1l);
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("not found with");
    }

    @Test
    void getCommentById_Comment_ResourceNotFoundException() {
        assertThatThrownBy(() -> {
            when(postRepository.findById(anyLong())).thenReturn(Optional.of(getPost()));
            when(commentRepository.findById(anyLong())).thenReturn(Optional.empty());
            commentService.getCommentById(1l,1l);
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("not found with");
    }

    @Test
    void updateComment() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(getPost()));
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(getComment()));
        when(commentRepository.save(any(Comment.class))).thenReturn(getComment());
        when(mapper.map(getComment(),CommentDto.class)).thenReturn(getCommentDto());
        CommentDto commentDto = commentService.updateComment(1l,1l,getCommentDto());
        assertThat(commentDto.getName()).isEqualTo(getComment().getName());
    }

    @Test
    void updateComment_BlogAPIException() {
        assertThatThrownBy(() -> {
            Post post = getPost();
            post.setId(2l);
            when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
            when(commentRepository.findById(anyLong())).thenReturn(Optional.of(getComment()));
            commentService.updateComment(1l,1l,getCommentDto());
        }).isInstanceOf(BlogAPIException.class)
                .hasMessage("Comment does not belongs to post");
    }

    @Test
    void updateComment_Post_ResourceNotFoundException() {
        assertThatThrownBy(() -> {
            when(postRepository.findById(anyLong())).thenReturn(Optional.empty());
            when(commentRepository.findById(anyLong())).thenReturn(Optional.of(getComment()));
            commentService.updateComment(1l,1l,getCommentDto());
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("not found with");
    }

    @Test
    void updateComment_Comment_ResourceNotFoundException() {
        assertThatThrownBy(() -> {
            when(postRepository.findById(anyLong())).thenReturn(Optional.of(getPost()));
            when(commentRepository.findById(anyLong())).thenReturn(Optional.empty());
            commentService.updateComment(1l,1l,getCommentDto());
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("not found with");
    }

    @Test
    void deleteComment() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(getPost()));
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(getComment()));
        commentService.deleteComment(1l,1l);
        verify(postRepository,times(1)).findById(anyLong());
        verify(commentRepository,times(1)).findById(anyLong());
    }

    @Test
    void deleteComment_BlogAPIException() {
        assertThatThrownBy(() -> {
            Post post = getPost();
            post.setId(2l);
            when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
            when(commentRepository.findById(anyLong())).thenReturn(Optional.of(getComment()));
            commentService.deleteComment(1l,1l);
        }).isInstanceOf(BlogAPIException.class)
                .hasMessage("Comment does not belongs to post");
    }

    @Test
    void deleteComment_Post_ResourceNotFoundException() {
        assertThatThrownBy(() -> {
            when(postRepository.findById(anyLong())).thenReturn(Optional.empty());
            when(commentRepository.findById(anyLong())).thenReturn(Optional.of(getComment()));
            commentService.deleteComment(1l,1l);
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("not found with");
    }

    @Test
    void deleteComment_Comment_ResourceNotFoundException() {
        assertThatThrownBy(() -> {
            when(postRepository.findById(anyLong())).thenReturn(Optional.of(getPost()));
            when(commentRepository.findById(anyLong())).thenReturn(Optional.empty());
            commentService.deleteComment(1l,1l);
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("not found with");
    }

    private Comment getComment(){
        Comment comment = new Comment();
        comment.setId(1l);
        comment.setName("Amu");
        comment.setEmail("amu@gmail.com");
        comment.setBody("I love Java");
        comment.setPost(getPost());
        return comment;
    }

    private CommentDto getCommentDto(){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(1l);
        commentDto.setName("Amu");
        commentDto.setEmail("amu@gmail.com");
        commentDto.setBody("I love Java");
        return commentDto;
    }

    private Post getPost(){
        Post post = new Post();
        post.setId(1l);
        post.setTitle("Title");
        post.setContent("Content");
        post.setDescription("Desc");
        return post;
    }
}