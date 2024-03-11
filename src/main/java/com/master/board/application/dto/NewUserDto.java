package com.master.board.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewUserDto(
        @NotNull
        @NotEmpty
        String first_name,
        @NotNull
        @NotEmpty
        String last_name,
        @NotNull
        @NotEmpty
        @Email
        String email,
        @NotNull
        @NotEmpty
        String address,
        @NotNull
        @NotEmpty
        String phone,
        @NotNull
        @NotEmpty
        String img_url,
        @NotNull
        @NotEmpty
        String role,
        @NotNull
        @NotEmpty
        String password
) {
}
