package com.master.board.application.dto;

import com.master.board.domain.models.Card;
import com.master.board.domain.models.CaseTypeFlow;
import com.master.board.domain.models.enums.CardItemState;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CardItemDto(
        @NotNull()
        @NotEmpty
         String hours,
        @NotNull
        @Min(1)
        Integer state,
        @NotNull
        @Min(1)
        Long caseTypeFlowId,
        @NotNull
        @Min(1)
        Long cardId
) {
}
