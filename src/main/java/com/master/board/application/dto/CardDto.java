package com.master.board.application.dto;

import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.enums.CardState;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.relational.core.sql.In;

import java.util.Date;

public record CardDto(
        @NotNull()
        @NotEmpty
        String name,
        @NotNull()
        @NotEmpty
        String description,
        @NotNull()
        Date dueDate,
        @NotNull()
        Date reminderDate,
        @NotNull()
        Boolean isActive,
        @NotNull
        @Min(1)
        Integer state,
        @NotNull
        @Min(1)
        Long caseTypeId,
        @NotNull
        @Min(1)
        Long projectId
) {
}
