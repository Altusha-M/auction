package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.LotDto;
import com.stc21.boot.auction.dto.UserDto;
import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.repository.LotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class LotServiceImpl implements LotService {

    // число карточек на странице
    public static final int SIZE = 5;

    private final ModelMapper modelMapper;

    private final LotRepository lotRepository;

    private final UserService userService;

    public LotServiceImpl(ModelMapper modelMapper, LotRepository lotRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.lotRepository = lotRepository;
        this.userService = userService;
    }

    @Override
    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    @Override
    @Transactional
    public void updateAllLots(List<Lot> lots) {
        lots.forEach(lot -> {
            lotRepository.updateCurrentPrice(calcCurrentPrice(lot), lot.getId());
        });
    }

    @Override
    public Page<LotDto> getPageOfHomePageLots(int page) {
        PageRequest pageRequest = PageRequest.of(page, SIZE);
        Page<Lot> lots = lotRepository.findAll(pageRequest);
        return lots.map(this::convertToLotDto);
    }

    @Override
    public Lot saveNewLot(LotDto lotDto, Authentication token) {
        UserDto authed = userService.findByUsername(token.getName());
        lotDto.setUserDto(authed);
        LocalDateTime nowDateTime = LocalDateTime.now();
        lotDto.setCreationTime(nowDateTime);
        lotDto.setTimeLastMod(nowDateTime);

        Lot lot = convertToEntity(lotDto);

        return lotRepository.save(lot);
    }

    private Double calcCurrentPrice(Lot lot) {
        Random random = new Random();
        Double max = lot.getMaxPrice();
        Double min = lot.getMinPrice();
        double randomValue = min + (max - min) * random.nextDouble();
        return randomValue;
    }

    // через мапер преобразуем в DTO. Руками устанавливаем DTO пользователя
    private LotDto convertToLotDto(Lot lot) {
        LotDto lotDto = modelMapper.map(lot, LotDto.class);
        UserDto userDto = modelMapper.map(lot.getUser(), UserDto.class);
        lotDto.setUserDto(userDto);
        return lotDto;
    }


    private Lot convertToEntity(LotDto lotDto) {
        return modelMapper.map(lotDto, Lot.class);
    }
}
