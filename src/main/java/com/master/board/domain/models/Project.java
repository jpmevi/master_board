package com.master.board.domain.models;

import com.master.board.adapters.out.entities.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public record Project(
        Integer id,
        User user,
        String name,
        String description,
        String background_url,
        Boolean isPublic,
        Boolean isActive,
        String disabled_reason,
        Date createdAt,
        Date updatedAt
        ) {

        public Project(ProjectEntity projectEntity) {
                this(
                        projectEntity.getId(),
                        new User(projectEntity.getUser()),
                        projectEntity.getName(),
                        projectEntity.getDescription(),
                        projectEntity.getBackground_url(),
                        projectEntity.getIsPublic(),
                        projectEntity.getIsActive(),
                        projectEntity.getDisabled_reason(),
                        projectEntity.getCreatedAt(),
                        projectEntity.getUpdatedAt()
                );
        }
        @Override
        public Integer id() {
                return id;
        }

        @Override
        public User user() {
                return user;
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

        @Override
        public String disabled_reason()
        {
                return disabled_reason;
        }

        @Override
        public Date createdAt() {
                return createdAt;
        }

        @Override
        public Date updatedAt() {
                return updatedAt;
        }
}
