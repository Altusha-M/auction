package com.stc21.boot.auction.entity;

import com.stc21.boot.auction.entity.enums.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users_table")
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_users_sequence")
    @SequenceGenerator(
            name = "pk_users_sequence",
            sequenceName = "users_id_seq",
            initialValue = 1000,
            allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    private Role role;

    @OneToMany(targetEntity = Lot.class)
    private List<Lot> userLots;
}
