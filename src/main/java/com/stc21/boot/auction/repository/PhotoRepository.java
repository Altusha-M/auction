package com.stc21.boot.auction.repository;

import com.stc21.boot.auction.entity.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Page<Photo> findByDeletedFalse(Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Photo p SET p.deleted = :isDeleted WHERE p.id = :photoId")
    int updateDeletedTo(@Param("photoId") Long photoId, @Param("isDeleted") boolean isDeleted);
}
