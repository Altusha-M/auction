package com.stc21.boot.auction.scheduler;

import com.stc21.boot.auction.dto.LotDto;
import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.service.LotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class LotPriceScheduler {

    private final LotService lotService;

    public LotPriceScheduler(LotService lotService) {
        this.lotService = lotService;
    }

    @Scheduled(cron = "0,30 * * * * *")
    public void updateLots() {
        log.info("< update Lots");
        List<LotDto> lots = lotService.getUnboughtLots(null, Pageable.unpaged()).toList();
        lotService.updateAllLots(lots);
        log.info("> update Lots");
    }
}
