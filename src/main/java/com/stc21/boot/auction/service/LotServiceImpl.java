package com.stc21.boot.auction.service;

import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
public class LotServiceImpl implements LotService {

    @Autowired
    private LotRepository lotRepository;

    @Override
    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    @Override
    @Transactional
    public void updateAllLots(List<Lot> lots) {
        lots.forEach(lot -> {
            lotRepository.updateCurrentPrice(calcCurrentPrice(lot), lot.getId());
        });
    }

    private Double calcCurrentPrice(Lot lot) {
        Random random = new Random();
        Double max = lot.getMaxPrice();
        Double min = lot.getMinPrice();
        double randomValue = min + (max - min) * random.nextDouble();
        return randomValue;
    }
}
