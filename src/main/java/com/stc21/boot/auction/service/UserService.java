package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.UserDto;
import com.stc21.boot.auction.dto.UserRegistrationDto;
import com.stc21.boot.auction.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto findByUsername(String username);
    UserDto findByEmail(String email);
    UserDto findByPhoneNumber(String phoneNumber);
    UserDto convertToDto(User user);

    List<String> fieldsWithErrors(UserRegistrationDto userRegistrationDto);
    User save(UserRegistrationDto userRegistrationDto);
}
