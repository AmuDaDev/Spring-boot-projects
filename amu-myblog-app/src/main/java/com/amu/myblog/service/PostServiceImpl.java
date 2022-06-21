package com.amu.myblog.service;

import com.amu.myblog.exception.ResourceNotFoundException;
import com.amu.myblog.model.Post;
import com.amu.myblog.payload.PostDto;
import com.amu.myblog.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Created 2022/06/18
 * @Author Amu
 */
@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //Save
        return maptoDto(postRepository.save(maptoEntity(postDto)));
    }

    @Override
    public List<PostDto> retrieveAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        //Sort
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        //Page
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        //get content for page object
        List<Post> postList = posts.getContent();
        List<PostDto> postDtoList = new ArrayList<>();
        postList.forEach(post -> {
            postDtoList.add(maptoDto(post));
        });
        return postDtoList;
    }

    @Override
    public PostDto retrievePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return maptoDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        return maptoDto(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    private PostDto maptoDto(Post post){
        PostDto postDto = mapper.map(post, PostDto.class);
        return postDto;
    }

    private Post maptoEntity(PostDto postDto){
        Post post = mapper.map(postDto, Post.class);
        return post;
    }
}
