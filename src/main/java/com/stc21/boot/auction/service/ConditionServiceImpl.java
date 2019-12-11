package com.stc21.boot.auction.service;

import com.stc21.boot.auction.entity.Condition;
import com.stc21.boot.auction.repository.ConditionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConditionServiceImpl implements ConditionService {

    private final ConditionRepository conditionRepository;

    public ConditionServiceImpl(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public List<Condition> findAll() {
        return conditionRepository.findAll();
    }
}
