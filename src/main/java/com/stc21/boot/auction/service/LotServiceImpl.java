package com.stc21.boot.auction.service;

import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotServiceImpl implements LotService {

    @Autowired
    private LotRepository lotRepository;

    @Override
    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }
}
