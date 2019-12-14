package com.stc21.boot.auction.controller;

import com.stc21.boot.auction.dto.LotDto;
import com.stc21.boot.auction.dto.UserDto;
import com.stc21.boot.auction.entity.Category;
import com.stc21.boot.auction.entity.City;
import com.stc21.boot.auction.entity.Condition;
import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.service.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping(path = "/")
public class HomeController {

    private final UserService userService;
    private final LotService lotService;
    private final CategoryService categoryService;
    private final CityService cityService;
    private final ConditionService conditionService;

    public HomeController(UserService userService, LotService lotService, CategoryService categoryService, CityService cityService, ConditionService conditionService) {
        this.userService = userService;
        this.lotService = lotService;
        this.categoryService = categoryService;
        this.cityService = cityService;
        this.conditionService = conditionService;
    }

    // возвращает результат постранично
    @GetMapping(path = "/")
    public String showHomePage(
            Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "-1") int categoryFilter,
            @RequestParam(defaultValue = "-1") int cityFilter,
            @RequestParam(defaultValue = "-1") int conditionFilter) {

        Lot exampleLot = new Lot();
        exampleLot .setCategory(  categoryFilter == -1 ? null :  categoryService.getById( categoryFilter));
        exampleLot     .setCity(      cityFilter == -1 ? null :      cityService.getById(    cityFilter));
        exampleLot.setCondition(conditionFilter == -1 ? null : conditionService.getById(conditionFilter));

        PageRequest pageRequest = PageRequest.of(page - 1, 5);

        model.addAttribute("categories",  categoryService.findAll());
        model.addAttribute(    "cities",      cityService.findAll());
        model.addAttribute("conditions", conditionService.findAll());

        Page<LotDto> pagedHomePageLots = lotService.getPaginated(exampleLot, pageRequest);
        model.addAttribute("lots", pagedHomePageLots);

        model.addAttribute("categoryFilterId", categoryFilter);
        model.addAttribute("cityFilterId", cityFilter);
        model.addAttribute("conditionFilterId", conditionFilter);

        return "home";
    }

    @GetMapping(path = "/account")
    public String showLotsPage(Model model) {
        List<Lot> allLots = lotService.getAllLots();
        List<UserDto> allUsers = userService.getAllUsers();
        model.addAttribute("lots", allLots);
        return "account";
    }
}
