package com.stc21.boot.auction.service;

import com.stc21.boot.auction.dto.PhotoDto;
import com.stc21.boot.auction.entity.Photo;
import com.stc21.boot.auction.repository.PhotoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final ModelMapper modelMapper;
    private final LotService lotService;

    public PhotoServiceImpl(PhotoRepository photoRepository, ModelMapper modelMapper, LotService lotService) {
        this.photoRepository = photoRepository;
        this.modelMapper = modelMapper;
        this.lotService = lotService;
    }

    @Override
    public List<PhotoDto> getAllPhotos() {
        return photoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PhotoDto> getPaginated(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.unsorted()));

        return photoRepository.findAll(pageRequest).map(this::convertToDto);
    }

    @Override
    public PhotoDto convertToDto(Photo photo) {
        if (photo == null) return null;

        PhotoDto photoDto = modelMapper.map(photo, PhotoDto.class);
        photoDto.setLot(lotService.convertToDto(photo.getLot()));

        return photoDto;
    }
}
