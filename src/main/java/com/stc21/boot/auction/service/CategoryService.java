package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.CategoryDto;
import com.stc21.boot.auction.entity.Category;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    List<CategoryDto> getAllSorted(Sort sort);
    CategoryDto convertToDto(Category category);
}
