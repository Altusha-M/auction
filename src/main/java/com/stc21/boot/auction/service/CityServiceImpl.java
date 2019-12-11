package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.CityDto;
import com.stc21.boot.auction.entity.City;
import com.stc21.boot.auction.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CityDto> getAllCities() {
        return cityRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CityDto> getAllSorted(Sort sort) {
        return cityRepository.findAll(sort).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CityDto convertToDto(City city) {
        if (city == null) return null;

        return modelMapper.map(city, CityDto.class);
    }
}
