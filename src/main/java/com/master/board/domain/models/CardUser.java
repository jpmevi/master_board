package com.master.board.domain.models;

import com.master.board.adapters.out.entities.CardItemEntity;
import com.master.board.adapters.out.entities.CardUserEntity;
import com.master.board.domain.models.enums.CardItemState;

import java.util.Date;

public record CardUser(
        Integer id,
        User user,
        Card card,
        CardItem cardItem
){
        public CardUser(CardUserEntity cardUserEntity) {
                this(
                        cardUserEntity.getId(),
                        new User(cardUserEntity.getUser()),
                        new Card(cardUserEntity.getCard()),
                        cardUserEntity.getCardItem() !=null ? new CardItem(cardUserEntity.getCardItem()) : null
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
}
