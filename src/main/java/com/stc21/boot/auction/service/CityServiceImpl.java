package com.stc21.boot.auction.service;

import com.stc21.boot.auction.entity.City;
import com.stc21.boot.auction.repository.CityRepository;
import com.stc21.boot.auction.repository.LotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService{

    private final ModelMapper modelMapper;
    private final CityRepository cityRepository;

    public CityServiceImpl(ModelMapper modelMapper, CityRepository cityRepository) {
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
