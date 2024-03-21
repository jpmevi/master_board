package com.master.board.application.dto;

import com.master.board.domain.models.enums.Role;
import jakarta.validation.constraints.*;

public record RegisterDto(
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
        @Min(1)
        @Max(3)
        Integer role,
        @NotNull
        @NotEmpty
        String password
) {
}
