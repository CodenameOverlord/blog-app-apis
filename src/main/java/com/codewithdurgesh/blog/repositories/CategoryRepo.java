package com.codewithdurgesh.blog.repositories;

import com.codewithdurgesh.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
