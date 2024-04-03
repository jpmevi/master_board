package com.master.board.domain.models;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.ProjectEntity;

import java.util.Date;

public record CaseType (
        Integer id,
        String name,
        String description,
        String labelColor,
        Date createdAt,
        Date updatedAt
){
        public CaseType(CaseTypeEntity caseTypeEntity) {
                this(
                        caseTypeEntity.getId(),
                        caseTypeEntity.getName(),
                        caseTypeEntity.getDescription(),
                        caseTypeEntity.getLabelColor(),
                        caseTypeEntity.getCreatedAt(),
                        caseTypeEntity.getUpdatedAt()
                );
        }

        @Override
        public Integer id() {
                return id;
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
        public String labelColor() {
                return labelColor;
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
