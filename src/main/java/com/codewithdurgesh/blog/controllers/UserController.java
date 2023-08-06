package com.codewithdurgesh.blog.controllers;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.services.UserService;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

//    POST - create User
//    Directly an entity must not be exposed in User Interface, rather we should use DTOS
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }
//    PUT  - Update User

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
        UserDto updatedUserDto = this.userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }
//    GETALLUSERS  - Fetch Users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    //    GET  - Fetch User
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId ){
        return new ResponseEntity<>(this.userService.getUserById(userId), HttpStatus.OK);
    }
//    DELETE - Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(ApiResponse.builder().success(true).message("User deleted successfully").build(), HttpStatus.OK);
    }

}
