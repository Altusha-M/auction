package com.stc21.boot.auction.controller;

import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.entity.Price;
import com.stc21.boot.auction.entity.User;
import com.stc21.boot.auction.service.LotService;
import com.stc21.boot.auction.service.PriceService;
import com.stc21.boot.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/")
public class WelcomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private LotService lotService;

    @Autowired
    private PriceService priceService;

    @GetMapping(path = "/")
    public String showWelcomePage(Model model) {
        return "welcomePage";
    }

    @GetMapping(path = "/lots")
    public String showLotsPage(Model model) {
        List<Lot> allLots = lotService.getAllLots();
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("lots", allLots);
        model.addAttribute("users", allUsers);
        return "lotsPage";
    }
}
