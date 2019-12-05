package com.stc21.boot.auction.controller;

import com.stc21.boot.auction.dto.UserDto;
import com.stc21.boot.auction.dto.UserRegistrationDto;
import com.stc21.boot.auction.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "register";
    }

    @PostMapping
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto,
            BindingResult result) {

        // TODO: write validation annotations
        // check for validity
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getRepeatPassword())) {
            result.rejectValue("repeatPassword", null, "Password doesn't match");
        }

        // check for existence
        UserDto existingUser = userService.findByUsername(userRegistrationDto.getUsername());
        if (existingUser != null) {
            result.rejectValue("username", null, "User with this username already exist");
        }

        existingUser = userService.findByEmail(userRegistrationDto.getEmail());
        if (existingUser != null) {
            result.rejectValue("email", null, "User with this email already exist");
        }

        existingUser = userService.findByPhoneNumber(userRegistrationDto.getPhoneNumber());
        if (existingUser != null) {
            result.rejectValue("phoneNumber", null, "User with this phoneNumber already exist");
        }

        if (result.hasErrors()) {
            return "register";
        }

        userService.save(userRegistrationDto);
        return "redirect:/register?success=true";
    }
}
