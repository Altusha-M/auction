package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.PhotoDto;
import com.stc21.boot.auction.entity.Photo;

import java.util.List;

public interface PhotoService {
    List<PhotoDto> getAllPhotos();
    PhotoDto convertToDto(Photo photo);
}
