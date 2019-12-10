package com.stc21.boot.auction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//
@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private RoleDto role;
    private CityDto city;
}
