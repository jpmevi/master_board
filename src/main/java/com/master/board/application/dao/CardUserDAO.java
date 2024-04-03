package com.master.board.application.dao;

import com.master.board.adapters.out.entities.*;
import com.master.board.application.dto.CardUserDto;
import com.master.board.domain.models.CardUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CardUserDAO {
    Optional<CardUser> find(Long id);
    Optional<CardUserEntity> findById(Long id);
    Page<CardUser> findAllCardUsers(Pageable pageable);
    List<CardUser> findAllCardUsersByCard(Long cardId);
    CardUser saveCardUser(CardUserDto input, UserEntity userEntity, CardEntity cardEntity, CardItemEntity cardItemEntity);
    CardUser saveCardUser(CardUserDto input, UserEntity userEntity, CardEntity cardEntity);
    void updateCardUser(CardUserEntity cardUser, CardUserDto input,UserEntity userEntity, CardEntity cardEntity, CardItemEntity cardItemEntity);
    void deleteCardUser(Long id);
}
