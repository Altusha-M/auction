package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.ConditionDto;
import com.stc21.boot.auction.entity.Condition;

import java.util.List;

public interface ConditionService {
    List<ConditionDto> getAllConditions();
    ConditionDto convertToDto(Condition condition);
}
