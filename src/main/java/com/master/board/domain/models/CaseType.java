package com.master.board.domain.models;

import com.master.board.adapters.out.entities.ProjectEntity;

import java.util.Date;

public record CaseType (
        Integer id,
        String name,
        String description,
        ProjectEntity project,
        String labelColor,
        Date createdAt,
        Date updatedAt
){
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
        public ProjectEntity project() {
                return project;
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
