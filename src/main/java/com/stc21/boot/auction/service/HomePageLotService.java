package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.HomePageLotDto;
import org.springframework.data.domain.Page;

public interface HomePageLotService {
    Page<HomePageLotDto> getPageHomePageLots(int page);
}
