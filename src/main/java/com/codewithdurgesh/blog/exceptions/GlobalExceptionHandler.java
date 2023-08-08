package com.codewithdurgesh.blog.exceptions;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message =  ex.getMessage();
        ApiResponse apiResponse = ApiResponse.builder().message(message).success(false).build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> res = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String feildName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            res.put(feildName, message);
        });

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
