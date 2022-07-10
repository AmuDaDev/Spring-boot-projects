package com.amu.myblog.service;

import com.amu.myblog.exception.ResourceNotFoundException;
import com.amu.myblog.model.Post;
import com.amu.myblog.payload.PostDto;
import com.amu.myblog.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @Created 2022/07/06
 * @Author Amu
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PostServiceImplTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private PostServiceImpl postService;

    @Test
    void createPost() {
        when(mapper.map(getPostDto(),Post.class)).thenReturn(getPost());
        when(postRepository.save(any(Post.class))).thenReturn(getPost());
        when(mapper.map(getPost(),PostDto.class)).thenReturn(getPostDto());
        PostDto postDto = postService.createPost(getPostDto());
        assertThat(postDto.getTitle()).isEqualTo(getPost().getTitle());
    }

    @Test
    void retrieveAllPosts_Asc() {
        when(postRepository.findAll(any(Pageable.class))).thenReturn(getPage());
        List<PostDto> postDtoList = postService.retrieveAllPosts(1,5,"id","asc");
        assertThat(postDtoList).isNotEmpty();
        assertThat(postDtoList.size()).isEqualTo(1);
    }

    @Test
    void retrieveAllPosts_Desc() {
        when(postRepository.findAll(any(Pageable.class))).thenReturn(getPage());
        List<PostDto> postDtoList = postService.retrieveAllPosts(1,5,"id","desc");
        assertThat(postDtoList).isNotEmpty();
        assertThat(postDtoList.size()).isEqualTo(1);
    }

    @Test
    void retrievePostById() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(getPost()));
        when(mapper.map(getPost(),PostDto.class)).thenReturn(getPostDto());
        PostDto postDto = postService.retrievePostById(1l);
        assertThat(postDto.getId()).isGreaterThan(0);
        assertThat(postDto.getTitle()).isEqualTo(getPost().getTitle());
    }

    @Test
    void retrievePostById_ResourceNotFoundException() {
        assertThatThrownBy(() -> {
            when(postRepository.findById(anyLong())).thenReturn(Optional.empty());
            postService.retrievePostById(1l);
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("not found with");
    }

    @Test
    void updatePost() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(getPost()));
        when(postRepository.save(any(Post.class))).thenReturn(getPost());
        when(mapper.map(getPost(),PostDto.class)).thenReturn(getPostDto());
        PostDto postDto = postService.updatePost(getPostDto(),1l);
        assertThat(postDto.getTitle()).isEqualTo(getPostDto().getTitle());
    }

    @Test
    void updatePost_ResourceNotFoundException() {
        assertThatThrownBy(() -> {
            when(postRepository.findById(anyLong())).thenReturn(Optional.empty());
            postService.updatePost(getPostDto(),1l);
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("not found with");
    }

    @Test
    void deletePost() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(getPost()));
        postService.deletePost(1l);
        verify(postRepository, times(1)).findById(anyLong());
    }

    @Test
    void deletePost_ResourceNotFoundException() {
        assertThatThrownBy(() -> {
            when(postRepository.findById(anyLong())).thenReturn(Optional.empty());
            postService.deletePost(1l);
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("not found with");
    }

    private Page getPage(){
        List<Post> posts = new ArrayList<>();
        posts.add(getPost());
        Page<Post> page = new PageImpl<>(posts);
        return page;
    }

    private Post getPost(){
        Post post = new Post();
        post.setId(1l);
        post.setTitle("Title");
        post.setContent("Content");
        post.setDescription("Desc");
        return post;
    }

    private PostDto getPostDto(){
        PostDto postDto = new PostDto();
        postDto.setId(1l);
        postDto.setTitle("Title");
        postDto.setContent("Content");
        postDto.setDescription("Desc");
        return postDto;
    }
}