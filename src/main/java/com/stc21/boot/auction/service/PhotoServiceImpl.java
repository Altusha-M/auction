package com.stc21.boot.auction.service;

import com.stc21.boot.auction.entity.Photo;
import com.stc21.boot.auction.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public void insertPhoto(Photo photo) {
        photoRepository.save(photo);
    }
}
