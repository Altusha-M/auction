package com.stc21.boot.auction.controller;

import com.stc21.boot.auction.dto.LotDto;
import com.stc21.boot.auction.dto.UserDto;
import com.stc21.boot.auction.entity.Category;
import com.stc21.boot.auction.entity.City;
import com.stc21.boot.auction.entity.Condition;
import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.service.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping(path = "/")
@SessionAttributes("queryParams")
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

    @ModelAttribute("queryParams")
    Map<String, String> queryParams() {
        return new HashMap<String, String>() {{
            put("page", "1");
            put("sortBy", "creationTime");
            put("sortDir", "asc");
            put("categoryFilter", "-1");
            put("cityFilter", "-1");
            put("conditionFilter", "-1");
        }};
    }

    // возвращает результат постранично
    @GetMapping(path = "/")
    public String showHomePage(
            Model model,
            @ModelAttribute("queryParams") Map<String, String> queryParams,
            @RequestParam Map<String, String> allParams) {
        queryParams.putAll(allParams);

        /* ----- */

        Lot exampleLot = new Lot();
        exampleLot .setCategory( categoryService.getById(Integer.parseInt(queryParams.get( "categoryFilter"))).orElse(null));
        exampleLot     .setCity(     cityService.getById(Integer.parseInt(queryParams.get(     "cityFilter"))).orElse(null));
        exampleLot.setCondition(conditionService.getById(Integer.parseInt(queryParams.get("conditionFilter"))).orElse(null));

        Sort sort = Sort.by(queryParams.get("sortDir").equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, queryParams.get("sortBy"));
        PageRequest pageRequest = PageRequest.of(Integer.parseInt(queryParams.get("page")) - 1, 5, sort);

        Page<LotDto> pagedHomePageLots = lotService.getPaginated(exampleLot, pageRequest);
        model.addAttribute("lots", pagedHomePageLots);

        /* ----- */

        model.addAttribute("categories",  categoryService.findAll());
        model.addAttribute(    "cities",      cityService.findAll());
        model.addAttribute("conditions", conditionService.findAll());

        model.addAttribute( "categoryFilterId", Integer.parseInt(queryParams.get( "categoryFilter")));
        model.addAttribute(     "cityFilterId", Integer.parseInt(queryParams.get(     "cityFilter")));
        model.addAttribute("conditionFilterId", Integer.parseInt(queryParams.get("conditionFilter")));

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
