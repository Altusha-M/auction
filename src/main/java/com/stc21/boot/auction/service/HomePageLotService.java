package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.LotDto;
import org.springframework.data.domain.Page;

public interface HomePageLotService {
    Page<LotDto> getPageHomePageLots(int page);
}
