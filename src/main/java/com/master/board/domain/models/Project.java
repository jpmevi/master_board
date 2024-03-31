package com.master.board.domain.models;

import com.master.board.adapters.out.entities.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        @Override
        public Integer id() {
                return id;
        }

        @Override
        public Long userId() {
                return userId;
        }

        @Override
        public String name() {
                return name;
        }

        @Override
        public String description() {
                return description;
        }

        @Override
        public String background_url() {
                return background_url;
        }

        @Override
        public Boolean isPublic() {
                return isPublic;
        }

        @Override
        public Boolean isActive() {
                return isActive;
        }
}
