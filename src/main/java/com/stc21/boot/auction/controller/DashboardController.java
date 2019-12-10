package com.stc21.boot.auction.controller;

import com.stc21.boot.auction.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final LotService lotService;
    private final UserService userService;
    private final PhotoService photoService;
    private final CategoryService categoryService;
    private final CityService cityService;
    private final ConditionService conditionService;

    public DashboardController(LotService lotService, UserService userService, PhotoService photoService, CategoryService categoryService, CityService cityService, ConditionService conditionService) {
        this.lotService = lotService;
        this.userService = userService;
        this.photoService = photoService;
        this.categoryService = categoryService;
        this.cityService = cityService;
        this.conditionService = conditionService;
    }

    @GetMapping()
    public String ugh(Model model) {
        model.addAttribute("lots", lotService.getAllLots());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("photos", photoService.getAllPhotos());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("conditions", conditionService.getAllConditions());

        return "dashboard";
    }
}
