package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.LotDto;
import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.repository.LotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LotServiceImpl implements LotService {

    private final LotRepository lotRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;
    private final CityService cityService;
    private final ConditionService conditionService;

    public LotServiceImpl(LotRepository lotRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService, CityService cityService, ConditionService conditionService) {
        this.lotRepository = lotRepository;
        this.modelMapper = modelMapper;
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
    public Page<LotDto> getPaginated(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.unsorted()));

        return lotRepository.findAll(pageRequest).map(this::convertToDto);
    }

    @Override
    public LotDto convertToDto(Lot lot) {
        if (lot == null) return null;

        LotDto lotDto = modelMapper.map(lot, LotDto.class);

        lotDto.setUser(userService.convertToDto(lot.getUser()));
        lotDto.setCategory(categoryService.convertToDto(lot.getCategory()));
        lotDto.setCity(cityService.convertToDto(lot.getCity()));
        lotDto.setCondition(conditionService.convertToDto(lot.getCondition()));

        return lotDto;
    }
}
