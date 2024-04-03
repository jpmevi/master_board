package com.master.board.domain.models;

import com.master.board.adapters.out.entities.CardActivityEntity;

import java.util.Date;

public record CardActivity(
        Integer id,
        User user,
        Card card,
        CardItem cardItem,
        String activity,
        Date createdAt
){
        public CardActivity(CardActivityEntity cardActivityEntity) {
                this(
                        cardActivityEntity.getId(),
                        new User(cardActivityEntity.getUser()),
                        new Card(cardActivityEntity.getCard()),
                        new CardItem(cardActivityEntity.getCardItem()),
                        cardActivityEntity.getActivity(),
                        cardActivityEntity.getCreatedAt()
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
        public Card card() {
                return card;
        }

        @Override
        public CardItem cardItem() {
                return cardItem;
        }

        @Override
        public String activity() {
                return activity;
        }

        @Override
        public Date createdAt() {
                return createdAt;
        }
}
