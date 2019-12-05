package com.stc21.boot.auction.dto;

import com.stc21.boot.auction.entity.enums.Condition;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LotDto {
    private LocalDateTime createdAt;
    private String description;
    private String categoryName;
    private Condition condition;

    private UserDto userDto;
}
