package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.HomePageLotDto;
import com.stc21.boot.auction.dto.UserDto;
import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.repository.LotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class HomePageLotServiceImpl implements HomePageLotService {

    // число карточек на странице
    public static final int SIZE = 5;

    private final ModelMapper modelMapper;
    private final LotRepository lotRepository;

    public HomePageLotServiceImpl(ModelMapper modelMapper, LotRepository lotRepository) {
        this.modelMapper = modelMapper;
        this.lotRepository = lotRepository;
    }

    @Override
    public Page<HomePageLotDto> getPageHomePageLots(int page) {
        PageRequest pageRequest = PageRequest.of(page, SIZE);
        Page<Lot> lots = lotRepository.findAll(pageRequest);
        return lots.map(this::convertToDto);
    }

    // через мапер преобразуем в DTO. Руками устанавливаем DTO пользователя
    private HomePageLotDto convertToDto(Lot lot) {
        HomePageLotDto dto = modelMapper.map(lot, HomePageLotDto.class);
        dto.setUserDto(new UserDto(lot.getUser()));
        return dto;
    }

    private Lot convertToEntity(HomePageLotDto lotDto) {
        return modelMapper.map(lotDto, Lot.class);
    }
}
