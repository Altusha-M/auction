package com.stc21.boot.auction.service;

import com.stc21.boot.auction.entity.Lot;

import java.util.List;

public interface LotService {
    List<Lot> getAllLots();
    void updateAllLots(List<Lot> lots);
}
