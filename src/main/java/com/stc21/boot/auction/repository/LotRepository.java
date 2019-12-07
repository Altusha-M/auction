package com.stc21.boot.auction.repository;

import com.stc21.boot.auction.entity.Lot;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends PagingAndSortingRepository<Lot, Long> {

}
