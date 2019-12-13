package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.LotDto;
import com.stc21.boot.auction.dto.LotDto;
import com.stc21.boot.auction.dto.UserDto;
import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.repository.LotRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class LotServiceImpl implements LotService {

    public static final int SIZE = 5;

    private final LotRepository lotRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public LotServiceImpl(LotRepository lotRepository, ModelMapper modelMapper, UserService userService) {
        this.lotRepository = lotRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public Page<LotDto> getPaginated(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.unsorted()));

        return lotRepository.findByDeletedFalse(pageRequest).map(this::convertToDto);
    }

    @Override
    public Page<LotDto> getPaginatedEvenDeleted(Pageable pageable) {
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

        lotDto.setUserDto(userService.convertToDto(lot.getUser()));
        lotDto.setCategory(lot.getCategory());
        lotDto.setCity(lot.getCity());
        lotDto.setCondition(lot.getCondition());

        return lotDto;
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
        Page<Lot> lots = lotRepository.findByDeletedFalse(pageRequest);
        return lots.map(this::convertToLotDto);
    }

    @Override
    public Lot saveNewLot(LotDto lotDto, Authentication token) {
        UserDto authed = userService.findByUsername(token.getName());
        lotDto.setUserDto(authed);
        LocalDateTime nowDateTime = LocalDateTime.now();
        lotDto.setCreationTime(nowDateTime);
        lotDto.setLastModTime(nowDateTime);

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

    @Override
    @Transactional
    public void setDeletedTo(long id, boolean newValue) {
        lotRepository.updateDeletedTo(id, newValue);
    }
}
