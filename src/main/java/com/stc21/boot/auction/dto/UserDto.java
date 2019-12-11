package com.stc21.boot.auction.dto;

import com.stc21.boot.auction.entity.User;
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

    public UserDto(User user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }
}
