package com.stc21.boot.auction.service;

import com.stc21.boot.auction.entity.City;
import com.stc21.boot.auction.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
