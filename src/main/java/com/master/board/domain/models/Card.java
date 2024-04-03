package com.master.board.domain.models;

import com.master.board.adapters.out.entities.CardEntity;
import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.domain.models.enums.CardState;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public record Card(





        Integer id,
        String name,
        String description,
        Date dueDate,
Date reminderDate,
Boolean isActive,
CardState state,
        CaseType caseType,
        Date createdAt,
        Date updatedAt
){
        public Card(CardEntity cardEntity) {
                this(
                        cardEntity.getId(),
                        cardEntity.getName(),
                        cardEntity.getDescription(),
                        cardEntity.getDueDate(),
                        cardEntity.getReminderDate(),
                        cardEntity.getIsActive(),
                        cardEntity.getState(),
                        new CaseType(cardEntity.getCaseType()),
                        cardEntity.getCreatedAt(),
                        cardEntity.getUpdatedAt()
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
        public Date dueDate() {
                return dueDate;
        }

        @Override
        public Date reminderDate() {
                return reminderDate;
        }

        @Override
        public Boolean isActive() {
                return isActive;
        }

        @Override
        public CardState state() {
                return state;
        }

        @Override
        public CaseType caseType() {
                return caseType;
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
