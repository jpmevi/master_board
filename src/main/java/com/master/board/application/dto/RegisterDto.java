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
        Double salary_per_hour,
        @NotNull
        @Min(0)
        @Max(2)
        Integer role,
        @NotNull
        @NotEmpty
        String password
) {
}
