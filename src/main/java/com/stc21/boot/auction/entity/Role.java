package com.stc21.boot.auction.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
@RequiredArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_roles_sequence")
    @SequenceGenerator(
            name = "pk_roles_sequence",
            sequenceName = "roles_id_seq",
            initialValue = 100,
            allocationSize = 1)
    private Long id;
    private String name;
}