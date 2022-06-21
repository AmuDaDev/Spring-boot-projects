package com.amu.myblog.service;

import com.amu.myblog.payload.PostDto;

import java.util.List;

/**
 * @Created 2022/06/18
 * @Author Amu
 */
public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> retrieveAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto retrievePostById(Long id);
    PostDto updatePost(PostDto postDto, long id);
    void deletePost(Long id);
}
