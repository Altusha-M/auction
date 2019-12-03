package com.stc21.boot.auction.service;

import com.stc21.boot.auction.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User findByUsername(String username);
}
