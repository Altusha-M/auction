package com.stc21.boot.auction.repository;

import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findAllByLot(Lot lot);
}
