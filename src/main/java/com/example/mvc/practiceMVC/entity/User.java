package com.example.mvc.practiceMVC.entity;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Data
public class User {

    private String id;

    @Getter
    @ToString.Exclude // Data 어노테이션 때문에 그렇다(ToString 자동생성)
    private String password;

    private String email;
    private String nickname;
    private Instant createdAt;
}
