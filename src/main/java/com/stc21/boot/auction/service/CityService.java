package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.CityDto;
import com.stc21.boot.auction.entity.City;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CityService {
    List<CityDto> getAllCities();
    List<CityDto> getAllSorted(Sort sort);
    CityDto convertToDto(City city);
}
