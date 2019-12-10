package com.stc21.boot.auction.repository;

import com.stc21.boot.auction.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}