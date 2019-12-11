package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.UserDto;
import com.stc21.boot.auction.dto.UserRegistrationDto;
import com.stc21.boot.auction.entity.User;
import com.stc21.boot.auction.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CityService cityService;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CityService cityService, RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.cityService = cityService;
        this.roleService = roleService;
    }

    @Override
    public List<UserDto> getAllUsers() {
        LinkedList<String> linkedList = new LinkedList<>();


        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserDto> getPaginated(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.unsorted()));

        return userRepository.findAll(pageRequest).map(this::convertToDto);
    }

    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public UserDto findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public UserDto findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public UserDto convertToDto(User user) {
        if (user == null) return null;

        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setCity(cityService.convertToDto(user.getCity()));
        userDto.setRole(roleService.convertToDto(user.getRole()));

        return userDto;
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
