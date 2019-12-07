package com.stc21.boot.auction.controller;

import com.stc21.boot.auction.dto.HomePageLotDto;
import com.stc21.boot.auction.service.HomePageLotService;
import com.stc21.boot.auction.service.LotService;
import com.stc21.boot.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(path = "/")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private LotService lotService;

    @Autowired
    private HomePageLotService homePageLotService;

    // возвращает результат постранично
    @GetMapping(path = "/")
    public String showWelcomePage(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<HomePageLotDto> pagedHomePageLots = homePageLotService.getPageHomePageLots(page);
        model.addAttribute("lots", pagedHomePageLots);
        return "home";
    }

    @GetMapping(path = "/account")
    public String showLotsPage(Model model) {
        return "account";
    }
}
