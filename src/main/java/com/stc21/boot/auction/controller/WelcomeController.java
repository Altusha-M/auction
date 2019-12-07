package com.stc21.boot.auction.controller;

import com.stc21.boot.auction.dto.UserDto;
import com.stc21.boot.auction.entity.Lot;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
        return "home";
    }

    @GetMapping(path = "/account")
    public String showLotsPage(Model model) {
        List<Lot> allLots = lotService.getAllLots();
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("lots", allLots);
        return "account";
    }

//    @GetMapping(path = "/id/{username}")
//    public ModelAndView findUserById(@PathVariable("username") String username) {
//        Map<String, Object> model = new HashMap<>();
//        final Optional<User> user = userService.findByUsername(username);
//        model.put("message", String.format("Hello %s!", user.orElseThrow(() -> new RuntimeException()).getFirstName()));
//        return new ModelAndView("lotsPage", model);
//    }
}
