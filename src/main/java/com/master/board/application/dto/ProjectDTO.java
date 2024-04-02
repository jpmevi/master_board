package com.master.board.application.dto;

import jakarta.validation.constraints.*;

public record ProjectDTO (
    @NotNull
    @NotEmpty
    String name,
    @NotNull
    @Min(1)
    Long user,
    String description,
    String background_url,
    Boolean is_active,
    Boolean is_public,
    String disabled_reason

){
}
