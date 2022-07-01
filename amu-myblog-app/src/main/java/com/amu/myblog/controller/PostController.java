package com.amu.myblog.controller;

import com.amu.myblog.payload.PostDto;
import com.amu.myblog.service.PostService;
import com.amu.myblog.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Created 2022/06/18
 * @Author Amu
 */
@Api(value = "CRUD APIs for Post resources")
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(value = "API to Create Post")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        PostDto responseDto = postService.createPost(postDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "API to Retrieve all Posts")
    @GetMapping
    public ResponseEntity<List<PostDto>> retrieveAllPosts(
            @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        List<PostDto> postDtoList = postService.retrieveAllPosts(pageNo,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(postDtoList);
    }

    @ApiOperation(value = "API to Retrieve Post by id")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> retrievePostById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.retrievePostById(id));
    }

    @ApiOperation(value = "API to Update Post")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.updatePost(postDto,id));
    }

    @ApiOperation(value = "API to Delete Post")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted",HttpStatus.OK);
    }
}
