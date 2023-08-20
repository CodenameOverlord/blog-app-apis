package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.PostDto;

import java.util.List;

public interface PostService {
//    create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
//    update
    PostDto updatePost(PostDto postDto, Integer postId);
//    delete
    void delete(Integer postId);
//    getAllPost
    List<PostDto> getAllPost();
//    getSinglePost
    PostDto getPostbyPostId(Integer postId);
//    getAllPostByCategory
    List<PostDto> getPostsByCategory(Integer categoryId);
//    getAllPostByUser
    List<PostDto> getPostByUser(Integer userId);
//    searchPosts
    List<Post> searchPosts(String keyword);
}
