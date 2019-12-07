package com.stc21.boot.auction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class HomePageLotDto {
    private long id;
    private String name;
    private Double currentPrice;
    private LocalDateTime creationTime;
    private LocalDateTime timeLastMod;
    private String description;
    private String categoryName;

    private UserDto userDto;
}
