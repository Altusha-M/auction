package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.UserDto;
import com.stc21.boot.auction.dto.UserRegistrationDto;
import com.stc21.boot.auction.entity.User;
import com.stc21.boot.auction.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findByUsername(String username) {
        return convertToDto(userRepository.findByUsername(username));
    }

    @Override
    public UserDto findByEmail(String email) {
        return convertToDto(userRepository.findByEmail(email));
    }

    @Override
    public UserDto findByPhoneNumber(String phoneNumber) {
        return convertToDto(userRepository.findByPhoneNumber(phoneNumber));
    }

    @Override
    public UserDto convertToDto(User user) {
        if (user == null) return null;

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        if (userRegistrationDto == null) {
            throw new NullPointerException("No user to register");
        }

        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setPassword(userRegistrationDto.getPassword());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPhoneNumber(userRegistrationDto.getPhoneNumber());
        return userRepository.save(user);
    }
}
