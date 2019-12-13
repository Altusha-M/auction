package com.stc21.boot.auction.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Entity
@Data
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_lots_sequence")
    @SequenceGenerator(
            name = "pk_lots_sequence",
            sequenceName = "lots_id_seq",
            initialValue = 1,
            allocationSize = 1)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column
    private String name;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "last_mod_time")
    private LocalDateTime lastModTime;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "condition_id", nullable = false)
    private Condition condition;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "current_price")
    private Double currentPrice;

    @Column(name = "max_price")
    private Double maxPrice;

    @Column(name = "min_price")
    private Double minPrice;

    @Column(name = "step_price")
    private Double stepPrice;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean deleted;
}