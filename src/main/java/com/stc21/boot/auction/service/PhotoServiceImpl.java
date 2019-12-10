package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.PhotoDto;
import com.stc21.boot.auction.entity.Photo;
import com.stc21.boot.auction.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final LotService lotService;

    public PhotoServiceImpl(PhotoRepository photoRepository, LotService lotService) {
        this.photoRepository = photoRepository;
        this.lotService = lotService;
    }

    @Override
    public List<PhotoDto> getAllPhotos() {
        return photoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PhotoDto convertToDto(Photo photo) {
        PhotoDto photoDto = new PhotoDto();
        photoDto.setId(photo.getId());
        photoDto.setLot(lotService.convertToDto(photo.getLot()));
        photoDto.setUrl(photo.getUrl());

        return photoDto;
    }
}
