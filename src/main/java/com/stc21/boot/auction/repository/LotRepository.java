package com.stc21.boot.auction.repository;

import com.stc21.boot.auction.entity.Lot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Lot l SET l.currentPrice = :currentPrice WHERE l.id = :lotId")
    int updateCurrentPrice(@Param("currentPrice") Double currentPrice, @Param("lotId") long lotId);

    Page<Lot> findByDeletedFalse(Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Lot l SET l.deleted = :isDeleted WHERE l.id = :lotId")
    int updateDeletedTo(@Param("lotId") Long lotId, @Param("isDeleted") boolean isDeleted);
}
