package com.master.board.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CaseTypeFlowDto(
        @NotNull
        @NotEmpty
        String stage,
        @NotNull
        @Min(1)
        Integer order,
        @NotNull
        @Min(1)
        Long caseTypeId
) {
}
