package com.stc21.boot.auction.dto;

import com.stc21.boot.auction.entity.Category;
import com.stc21.boot.auction.entity.City;
import com.stc21.boot.auction.entity.Condition;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LotDto {
    private Long id;

    @NotNull
    @Size(min = 5, max = 50)
    private String name;

    private LocalDateTime creationTime;
    private LocalDateTime lastModTime;

    @NotNull
    @Size(min = 5, max = 150)
    private String description;

    private Category category;
    private Condition condition;
    private City city;

    @NotNull
    @Min(value = 0)
    @Max(value = 50000)
    private Double currentPrice;
    @NotNull
    @Min(value = 0)
    @Max(value = 50000)
    private Double maxPrice;
    @NotNull
    @Min(value = 0)
    @Max(value = 50000)
    private Double minPrice;

    private Double stepPrice;

    private UserDto userDto;
    private Boolean deleted;
}
