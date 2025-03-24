package com.example.mvc.practiceMVC.service;

import com.example.mvc.practiceMVC.dto.UserRegisterRequest;
import com.example.mvc.practiceMVC.entity.User;
import com.example.mvc.practiceMVC.jwt.JwtUtil;
import com.example.mvc.practiceMVC.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public void register(UserRegisterRequest request) {
        if (userRepository.existsById(request.getId())) {
            throw new RuntimeException("이미 존재하는 ID입니다.");
        }

        User user = new User();
        user.setId(request.getId());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setEmail(request.getEmail());
        user.setNickname(request.getNickname());
        user.setCreatedAt(Instant.now());

        userRepository.save(user);
    }

    public String login(String id, String password) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.generateToken(id);
    }

    public boolean existsById(String id) {
        return userRepository.findById(id).isPresent();
    }
}
