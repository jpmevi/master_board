package com.master.board.domain.models;

public record Project(
        Integer id,
        Long userId,
        String name,
        String description,
        String background_url,
        Boolean isPublic,
        Boolean isActive
        ) {

}
