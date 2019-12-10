package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.LotDto;
import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.repository.LotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LotServiceImpl implements LotService {

    private final LotRepository lotRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final CityService cityService;
    private final ConditionService conditionService;

    public LotServiceImpl(LotRepository lotRepository, UserService userService, CategoryService categoryService, CityService cityService, ConditionService conditionService) {
        this.lotRepository = lotRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.cityService = cityService;
        this.conditionService = conditionService;
    }

    @Override
    public List<LotDto> getAllLots() {
        return lotRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LotDto convertToDto(Lot lot) {
        LotDto lotDto = new LotDto();
        lotDto.setId(lot.getId());
        lotDto.setUser(userService.convertToDto(lot.getUser()));

        lotDto.setTitle(lot.getTitle());
        lotDto.setDescription(lot.getDescription());

        lotDto.setCreationTime(lot.getCreationTime());
        lotDto.setTimeLastMod(lot.getTimeLastMod());

        lotDto.setCurrentPrice(lot.getCurrentPrice());
        lotDto.setMaxPrice(lot.getMaxPrice());
        lotDto.setMinPrice(lot.getMinPrice());
        lotDto.setStepPrice(lot.getStepPrice());

        lotDto.setCategory(categoryService.convertToDto(lot.getCategory()));
        lotDto.setCity(cityService.convertToDto(lot.getCity()));
        lotDto.setCondition(conditionService.convertToDto(lot.getCondition()));

        return lotDto;
    }
}
