package com.stc21.boot.auction.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "prices")
@RequiredArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_prices_sequence")
    @SequenceGenerator(
            name = "pk_prices_sequence",
            sequenceName = "prices_id_seq",
            initialValue = 1000,
            allocationSize = 1)
    private long id;

    @Column(name = "min_price")
    private Integer minPrice;

    @Column(name = "max_price")
    private Integer maxPrice;

    @Column(name = "current_price")
    private Integer currentPrice;

    @Column(name = "step_size_value")
    private Integer stepSizeValue;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Lot lot;

}
