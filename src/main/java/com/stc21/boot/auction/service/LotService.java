package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.LotDto;
import com.stc21.boot.auction.entity.Lot;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface LotService {
    List<Lot> getAllLots();

    List<Lot> getAllLotsByUsername(Authentication token);

    void updateAllLots(List<Lot> lots);

    Page<LotDto> getPageOfHomePageLots(int page);

    Lot saveNewLot(LotDto lot, Authentication token);
}
