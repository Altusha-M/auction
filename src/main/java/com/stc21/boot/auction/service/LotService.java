package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.LotDto;
import com.stc21.boot.auction.entity.Lot;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LotService {
    List<Lot> getAllLots();

    void updateAllLots(List<Lot> lots);

    Page<LotDto> getPageOfHomePageLots(int page);

    Lot saveNewLot(LotDto lotDto, Authentication token, MultipartFile[] images);
}
