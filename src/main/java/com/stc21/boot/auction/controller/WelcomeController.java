package com.stc21.boot.auction.controller;

import com.stc21.boot.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class WelcomeController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/")
    public String showWelcomePage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "welcomePage";
    }
}
