package com.master.board.application.dto;

import jakarta.validation.constraints.*;

public record ProjectDTO (
    @NotNull
    @NotEmpty
    String name


){
}
