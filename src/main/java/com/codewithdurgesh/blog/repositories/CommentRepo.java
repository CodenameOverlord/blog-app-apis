package com.codewithdurgesh.blog.repositories;

import com.codewithdurgesh.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
