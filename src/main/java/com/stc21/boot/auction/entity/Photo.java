package com.stc21.boot.auction.entity;

import lombok.Data;
import javax.persistence.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
@Data
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_photo_sequence")
    @SequenceGenerator(
            name = "pk_photo_sequence",
            sequenceName = "photo_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "lot_id", nullable = false)
    private Lot lot;

    @Column
    private String url;
}
