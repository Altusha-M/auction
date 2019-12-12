package com.stc21.boot.auction.controller;

import com.stc21.boot.auction.dto.LotDto;
import com.stc21.boot.auction.entity.Category;
import com.stc21.boot.auction.entity.City;
import com.stc21.boot.auction.entity.Condition;
import com.stc21.boot.auction.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/add")
public class AddLotController {

    private final ConditionService conditionService;
    private final CityService cityService;
    private final CategoryService categoryService;
    private final LotService lotService;
    private final UserService userService;

    public AddLotController(ConditionService conditionService, CityService cityService, CategoryService categoryService, LotService lotService, UserService userService) {
        this.conditionService = conditionService;
        this.cityService = cityService;
        this.categoryService = categoryService;
        this.lotService = lotService;
        this.userService = userService;
    }

    @ModelAttribute(name = "categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @ModelAttribute(name = "cities")
    public List<City> cities() {
        return cityService.getAllCities();
    }

    @ModelAttribute(name = "conditions")
    public List<Condition> conditions() {
        return conditionService.findAll();
    }
    @ModelAttribute(name = "newLot")
    public LotDto newLot() {
        return new LotDto();
    }



    @GetMapping(path = "/lot")
    public String showAddLotPage(Model model) {
        return "addLot";
    }

    @PostMapping(path = "/lot")
    public String processAddLotPage(Model model,
                                    @RequestPart("lotImages") MultipartFile[] lotImages,
                                    @AuthenticationPrincipal Authentication token,
                                    @Valid @ModelAttribute(value = "newLot") LotDto newLot,
                                    Errors result) {
        if (result.hasErrors()) {
            return "addLot";
        }


        lotService.saveNewLot(newLot, token);
        return "redirect:/";
    }
}
