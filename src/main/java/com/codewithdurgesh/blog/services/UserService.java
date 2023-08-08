package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUserById(Integer userID);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
