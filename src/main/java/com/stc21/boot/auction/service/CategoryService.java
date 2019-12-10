package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.CategoryDto;
import com.stc21.boot.auction.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto convertToDto(Category category);
}
