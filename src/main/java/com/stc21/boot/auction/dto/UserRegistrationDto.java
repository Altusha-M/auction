package com.stc21.boot.auction.dto;

import com.stc21.boot.auction.dto.validators.annotations.EqualPasswords;
import com.stc21.boot.auction.dto.validators.annotations.ValidEmail;
import com.stc21.boot.auction.dto.validators.annotations.ValidPhoneNumber;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@EqualPasswords
public class UserRegistrationDto {
    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @NotNull
    @Size(min = 3, max = 20)
    private String password;
    private String repeatPassword;

    @ValidEmail
    private String email;
    @ValidPhoneNumber
    private String phoneNumber;
}
