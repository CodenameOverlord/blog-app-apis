package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.payloads.CommentDto;

public interface CommentService {
//    create
    public CommentDto createComment(CommentDto commentDto, Integer postId);
    public void deleteComment(Integer commentId);


//
}
