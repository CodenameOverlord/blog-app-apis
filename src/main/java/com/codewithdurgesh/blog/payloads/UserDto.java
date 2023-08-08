package com.codewithdurgesh.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Integer id;
    @NotEmpty
    @Size(min = 4, message = "Username must be minimum 4 characters")
    private String name;
    @Email(message = "Email Address is not valid")
    private String email;
    @NotEmpty
    @Size(min = 4, max = 10, message = "Password must be min 3 characters and max 10 characters.")
    private String password;
    @NotEmpty
    private String about;
}
