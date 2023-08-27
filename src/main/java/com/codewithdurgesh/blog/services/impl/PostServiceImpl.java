package com.codewithdurgesh.blog.services.impl;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.repositories.CategoryRepo;
import com.codewithdurgesh.blog.repositories.PostRepo;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(post);
        PostDto updatedPostDto = this.modelMapper.map(updatedPost, PostDto.class);
        return updatedPostDto;
    }

    @Override
    public void delete(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
//        int pageSize = 5;
//        int pageNumber =1;
        Sort sort = null;
        if("desc".equalsIgnoreCase(sortDir)){
            sort = Sort.by(sortBy).descending();
        }
        else {
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = this.postRepo.findAll(pageable);
        List<Post> posts = pagePost.getContent();
//        List<Post> posts = this.postRepo.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        for(Post post: posts){
            postDtos.add(this.modelMapper.map(post, PostDto.class));
        }
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostbyPostId(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->
            new ResourceNotFoundException("Post", "postID", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId", categoryId));
        List <Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> postDtos = new ArrayList<>();
        for(Post post : posts){
            postDtos.add(this.modelMapper.map(post, PostDto.class));
        }
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = new ArrayList<>();
        for(Post post : posts){
            postDtos.add(this.modelMapper.map(post, PostDto.class));
        }
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List <PostDto> postDtos = new ArrayList<>();
        for(Post post: posts){
            postDtos.add(modelMapper.map(post, PostDto.class));
        }
        return postDtos;
    }
    @Override
    public List<PostDto> searchPosts2(String keyword) {
        List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
        List <PostDto> postDtos = new ArrayList<>();
        for(Post post: posts){
            postDtos.add(modelMapper.map(post, PostDto.class));
        }
        return postDtos;
    }
}
