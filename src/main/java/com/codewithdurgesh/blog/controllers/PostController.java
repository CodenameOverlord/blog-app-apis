package com.codewithdurgesh.blog.controllers;

import com.codewithdurgesh.blog.config.ApplicationConstants;
import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
//    create
    @Autowired
    private PostService postService;
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){
        PostDto createPostDto = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createPostDto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(
            @PathVariable Integer userId
    ){
        List<PostDto> postDtos = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtos = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }
//    GetAllPosts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = ApplicationConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = ApplicationConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = ApplicationConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ApplicationConstants.SORT_DIR, required = false) String sortDir
    ){
        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK );
    }
//    GetPostByPostId
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getByPostId(@PathVariable Integer postId){
        PostDto postDto = this.postService.getPostbyPostId(postId);
        return  new ResponseEntity<>(postDto, HttpStatus.OK);
    }
//    deletePost
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.delete(postId);
        return  new ResponseEntity<>(ApiResponse.builder().success(true).message("Post with id "+postId+ "is successfully deleted.").build(), HttpStatus.OK);
    }
//    UpdatePost
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePostByPostId(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto postDtoUpdated = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(postDtoUpdated, HttpStatus.OK);
    }
//    Search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords ){
        List<PostDto> postDtos = this.postService.searchPosts(keywords);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/posts2/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle2(@PathVariable("keywords") String keywords ){
        List<PostDto> postDtos = this.postService.searchPosts(keywords);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

}


