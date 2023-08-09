package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.repositories.CategoryRepo;

import java.util.List;

public interface CategoryService {
    //create
    public CategoryDto createCategory(CategoryDto categoryDto);
    //update
    public CategoryDto udpateCategory(CategoryDto categoryDto, Integer categoryId);
    //delete
    public void deleteCategory(Integer categoryId);
    //get
    public CategoryDto getCategory(Integer categoryId);
    //getAll
    List<CategoryDto> getCategories();
}
