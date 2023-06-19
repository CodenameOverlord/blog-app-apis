package com.codewithdurgesh.blog.repositories;

import com.codewithdurgesh.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

}
