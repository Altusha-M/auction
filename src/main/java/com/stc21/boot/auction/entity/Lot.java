package com.stc21.boot.auction.entity;

import com.stc21.boot.auction.entity.enums.Condition;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lots")
@RequiredArgsConstructor
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_lots_sequence")
    @SequenceGenerator(
            name = "pk_lots_sequence",
            sequenceName = "lots_id_seq",
            initialValue = 1000,
            allocationSize = 1)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String description;

    @Column(name = "category_name")
    private String categoryName;

    @Enumerated(EnumType.STRING)
    private Condition condition;
}
