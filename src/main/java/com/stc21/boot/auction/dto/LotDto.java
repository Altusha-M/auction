package com.stc21.boot.auction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LotDto {
    private long id;
    private String title;
    private UserDto user;
    private LocalDateTime creationTime;
    private LocalDateTime timeLastMod;
    private String description;
    private CategoryDto category;
    private ConditionDto condition;
    private CityDto city;
    private Double currentPrice;
    private Double maxPrice;
    private Double minPrice;
    private Double stepPrice;
}
