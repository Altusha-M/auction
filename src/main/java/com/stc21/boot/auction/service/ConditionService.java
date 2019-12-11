package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.ConditionDto;
import com.stc21.boot.auction.entity.Condition;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ConditionService {
    List<ConditionDto> getAllConditions();
    List<ConditionDto> getAllSorted(Sort sort);
    ConditionDto convertToDto(Condition condition);
}
