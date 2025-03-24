package com.example.mvc.practiceMVC.repository;

import com.example.mvc.practiceMVC.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private final Map<String, User> userMap = new ConcurrentHashMap<>();

    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    public void save(User user) {
        userMap.put(user.getId(), user);
    }

    public boolean existsById(String id) {
        return userMap.containsKey(id);
    }
}
