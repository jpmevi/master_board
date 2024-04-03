package com.master.board.application.dto;

import com.master.board.domain.models.Card;
import com.master.board.domain.models.CardItem;
import com.master.board.domain.models.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CardActivityDto(
        @NotNull
        @Min(1)
        Long userId,
        @NotNull
        @Min(1)
        Long cardId,
        @Min(1)
        Long cardItemId,
        @NotNull()
        @NotEmpty
        String activity
) {
}
