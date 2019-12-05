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

        userService
                .fieldsWithErrors(userRegistrationDto)
                .forEach(
                        fieldName->result.rejectValue(
                                fieldName,
                                null,
                                "Username with this "+fieldName+" already exist. Pick another one."));

        if (result.hasErrors()) {
            return "register";
        }

        userService.save(userRegistrationDto);
        return "redirect:/register?success=true";
    }
}
