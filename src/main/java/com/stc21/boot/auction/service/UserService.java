package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.UserDto;
import com.stc21.boot.auction.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto findByUsername(String username);
    UserDto convertToDto(User user);
}
