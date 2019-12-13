package com.stc21.boot.auction.repository;

import com.stc21.boot.auction.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Lot l SET l.currentPrice = :currentPrice WHERE l.id = :lotId")
    int updateCurrentPrice(@Param("currentPrice") Long currentPrice, @Param("lotId") long lotId);

    List<Lot> findAllByUserUsername(String username);
}
