package com.example.movieapp.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    @NotEmpty(message = "K được để trống name")
    String name;
    @NotEmpty(message = "K được để trống email")
    @Email(message = "Email ko đúng định dạng")
    String email;
    @NotEmpty(message = "K được để trống password")
    String password;
    @NotEmpty(message = "K được để trống confirm password")
    String confirmPassword;
}
