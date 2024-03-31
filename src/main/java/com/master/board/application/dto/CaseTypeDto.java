package com.master.board.application.dto;

import jakarta.validation.constraints.*;

public record CaseTypeDto(
        @NotNull()
        @NotEmpty
        String name,
        @NotNull
        @NotEmpty
        String description,
        @NotNull
        @NotEmpty
        String labelColor,
        @NotNull
        @Min(1)
        Integer projectId
) {
}
