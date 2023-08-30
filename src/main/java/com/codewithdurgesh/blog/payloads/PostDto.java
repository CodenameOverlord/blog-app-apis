package com.codewithdurgesh.blog.payloads;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Comment;
import com.codewithdurgesh.blog.entities.User;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private UserDto user;
    private CategoryDto category;
    private Set<CommentDto> comments = new HashSet<>();
}
