package com.master.board.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CommentDto(
        @NotNull()
        @NotEmpty
        String comment,
        @NotNull
        @Min(1)
        Long userId,
        @NotNull
        @Min(1)
        Long cardItemId
) {
}
