package com.master.board.domain.models;

import com.master.board.adapters.out.entities.CommentEntity;

import java.util.Date;

public record Comment(
        Integer id,
        String comment,
        User user,
        Long cardItem,
        Date createdAt,
        Date updatedAt
){
        public Comment(CommentEntity commentEntity) {
                this(
                        commentEntity.getId(),
                        commentEntity.getComment(),
                        new User(commentEntity.getUser()),
                        commentEntity.getCardItemId(),
                        commentEntity.getCreatedAt(),
                        commentEntity.getUpdatedAt()
                );
        }

        @Override
        public Integer id() {
                return id;
        }

        @Override
        public String comment() {
                return comment;
        }

        @Override
        public User user() {
                return user;
        }

        @Override
        public Long cardItem() {
                return cardItem;
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
