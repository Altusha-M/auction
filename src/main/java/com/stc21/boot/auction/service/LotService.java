package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.LotDto;
import com.stc21.boot.auction.entity.Lot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LotService {
    List<LotDto> getAllLots();
    Page<LotDto> getPaginated(Pageable pageable);
    LotDto convertToDto(Lot lot);
}
