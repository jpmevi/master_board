package com.master.board.domain.models;

import com.master.board.adapters.out.entities.ProjectEntity;

public record Project(
        Integer id,
        Long userId,
        String name,
        String description,
        String background_url,
        Boolean isPublic,
        Boolean isActive
        ) {
        public Project(ProjectEntity projectEntity) {
                this(
                        projectEntity.getId(),
                        projectEntity.getUserId(),
                        projectEntity.getName(),
                        projectEntity.getDescription(),
                        projectEntity.getBackground_url(),
                        projectEntity.getIsPublic(),
                        projectEntity.getIsActive()
                );
        }
}
