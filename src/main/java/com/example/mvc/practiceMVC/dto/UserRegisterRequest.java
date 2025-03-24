package com.example.mvc.practiceMVC.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterRequest {
    @Size(min = 6, max = 30)
    private String id;

    @Pattern(regexp = "^(?=(.*[A-Za-z]){2,})(?=(.*\\d){2,})(?=(.*[!@#$%^&*]){2,}).{12,50}$")
    private String password;

    @Email // 이메일 형식인지 검사
    @Size(max = 100)
    private String email;

    @Size(max = 50)
    private String nickname;
}
