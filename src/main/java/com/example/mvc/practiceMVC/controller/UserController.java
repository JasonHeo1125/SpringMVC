package com.example.mvc.practiceMVC.controller;

import com.example.mvc.practiceMVC.dto.UserRegisterRequest;
import com.example.mvc.practiceMVC.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterRequest request) {
        userService.register(request);
        return ResponseEntity.ok(new HashMap<>() {{
            put("success", true);
            put("message", "회원 가입이 완료되었습니다.");
        }});
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody HashMap<String, String> request) {
        String token = userService.login(request.get("id"), request.get("password"));
        return ResponseEntity.ok(new HashMap<>() {{
            put("success", true);
            put("token", token);
        }});
    }
}
