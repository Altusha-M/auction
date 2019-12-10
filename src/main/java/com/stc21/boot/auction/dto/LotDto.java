package com.stc21.boot.auction.dto;

import com.stc21.boot.auction.entity.Category;
import com.stc21.boot.auction.entity.Condition;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LotDto {
    private long id;
    private String name;
    private Double currentPrice;
    private LocalDateTime creationTime;
    private LocalDateTime timeLastMod;
    private String description;
    private Category category;
    private Condition condition;

    private UserDto userDto;
}
