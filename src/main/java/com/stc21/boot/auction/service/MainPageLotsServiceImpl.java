package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.LotDto;
import com.stc21.boot.auction.dto.UserDto;
import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainPageLotsServiceImpl implements MainPageLotsService {

    @Autowired
    private LotRepository lotRepository;

    @Override
    public List<LotDto> getMainPageLotDtos() {
        List<Lot> lotsFromDb = lotRepository.findAll();
        List<LotDto> lotDtoList = new ArrayList<>();
        lotsFromDb.forEach(lot -> {
            UserDto userDto = new UserDto();
            LotDto lotDto = new LotDto();
            userDto.setFirstName(lot.getUser().getFirstName());
            userDto.setLastName(lot.getUser().getUsername());
            userDto.setUsername(lot.getUser().getUsername());
        //    userDto.setPhoneNumber(lot.getUser().getPhoneNumber());
            userDto.setEmail(lot.getUser().getFirstName());

            lotDto.setUserDto(userDto);

            lotDto.setCategoryName(lot.getCategoryName());
            lotDto.setCondition(lot.getCondition());
            lotDto.setCreatedAt(lot.getCreatedAt());
            lotDto.setDescription(lot.getDescription());

            lotDtoList.add(lotDto);
        });
        return lotDtoList;
    }
}
