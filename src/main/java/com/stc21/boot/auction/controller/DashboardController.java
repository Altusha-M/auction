package com.stc21.boot.auction.controller;

import com.stc21.boot.auction.service.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.awt.print.Pageable;
import java.util.Optional;
import java.util.Set;

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
    public String displayEverything(
            Model model,
            @RequestParam(required = false) String section,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size) {

        Set<String> possibleSections = Set.of(
                "lots", "users", "photos", "categories", "cities", "conditions"
        );

        String currentSection = section != null && possibleSections.contains(section) ? section : "lots";
        int currentPage = Math.max(page.orElse(1), 1);
        int pageSize = Math.max(size.orElse(5), 1);

        PageRequest defaultPageable = PageRequest.of(0, 5);
        PageRequest specificPageable = PageRequest.of(currentPage - 1, pageSize);

        model.addAttribute("section", currentSection);

        model.addAttribute(      "lots",       lotService.getPaginated(  currentSection.equals("lots") ? specificPageable : defaultPageable));
        model.addAttribute(     "users",      userService.getPaginated( currentSection.equals("users") ? specificPageable : defaultPageable));
        model.addAttribute(    "photos",     photoService.getPaginated(currentSection.equals("photos") ? specificPageable : defaultPageable));
        model.addAttribute("categories",  categoryService.getAllCategories());
        model.addAttribute(    "cities",      cityService    .getAllCities());
        model.addAttribute("conditions", conditionService.getAllConditions());

        return "dashboard";
    }
}
