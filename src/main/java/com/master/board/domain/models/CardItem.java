package com.master.board.domain.models;

import com.master.board.adapters.out.entities.CardItemEntity;
import com.master.board.domain.models.Card;
import com.master.board.domain.models.CaseTypeFlow;
import com.master.board.domain.models.User;
import com.master.board.domain.models.enums.CardItemState;

import java.util.Date;

public record CardItem(
        Integer id,
        String hours,
        CardItemState state,
        CaseTypeFlow caseTypeFlow,
        Card card,
        Date createdAt,
        Date updatedAt
){
        public CardItem(CardItemEntity cardItemEntity) {
                this(
                        cardItemEntity.getId(),
                        cardItemEntity.getHours(),
                        cardItemEntity.getState(),
                        new CaseTypeFlow(cardItemEntity.getCaseTypeFlow()),
                        new Card(cardItemEntity.getCard()),
                        cardItemEntity.getCreatedAt(),
                        cardItemEntity.getUpdatedAt()
                );
        }

        @Override
        public Integer id() {
                return id;
        }

        @Override
        public String hours() {
                return hours;
        }

        @Override
        public CardItemState state() {
                return state;
        }

        @Override
        public CaseTypeFlow caseTypeFlow() {
                return caseTypeFlow;
        }

        @Override
        public Card card() {
                return card;
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
