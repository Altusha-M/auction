package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.CityDto;
import com.stc21.boot.auction.entity.City;

import java.util.List;

public interface CityService {
    List<CityDto> getAllCities();
    CityDto convertToDto(City city);
}
