package com.stc21.boot.auction.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Entity
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_city_sequence")
    @SequenceGenerator(
            name = "pk_city_sequence",
            sequenceName = "city_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    private String name;
}
