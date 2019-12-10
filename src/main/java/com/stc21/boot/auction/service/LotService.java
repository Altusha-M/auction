package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.LotDto;
import com.stc21.boot.auction.entity.Lot;

import java.util.List;

public interface LotService {
    List<LotDto> getAllLots();
    LotDto convertToDto(Lot lot);
}
