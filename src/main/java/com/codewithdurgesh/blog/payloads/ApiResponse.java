package com.codewithdurgesh.blog.payloads;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiResponse {
    private String message;
    private boolean success;
}
