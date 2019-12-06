package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.UserDto;
import com.stc21.boot.auction.dto.UserRegistrationDto;
import com.stc21.boot.auction.entity.User;
import com.stc21.boot.auction.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<String> fieldsWithErrors(UserRegistrationDto userRegistrationDto) {
        List<String> result = new ArrayList<>();

        UserDto existingUser = findByUsername(userRegistrationDto.getUsername());
        if (existingUser != null)
            result.add("username");

        existingUser = findByEmail(userRegistrationDto.getEmail());
        if (existingUser != null)
            result.add("email");

        existingUser = findByPhoneNumber(userRegistrationDto.getPhoneNumber());
        if (existingUser != null) {
            result.add("phoneNumber");
        }

        return result;
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        if (userRegistrationDto == null)
            throw new NullPointerException("No userRegistrationDto to save");

        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setPassword(userRegistrationDto.getPassword()); // TODO: to hash
        user.setEmail(userRegistrationDto.getEmail().equals("") ? null : userRegistrationDto.getEmail());
        user.setPhoneNumber(userRegistrationDto.getPhoneNumber().equals("") ? null : userRegistrationDto.getPhoneNumber());

        return userRepository.save(user);
    }
}
