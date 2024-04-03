package com.master.board.application.dao;

import com.master.board.adapters.out.entities.CardEntity;
import com.master.board.adapters.out.entities.CardItemEntity;
import com.master.board.adapters.out.entities.CardActivityEntity;
import com.master.board.adapters.out.entities.UserEntity;
import com.master.board.application.dto.CardActivityDto;
import com.master.board.domain.models.CardActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CardActivityDAO {
    Optional<CardActivity> find(Long id);
    Optional<CardActivityEntity> findById(Long id);
    Page<CardActivity> findAllCardActivity(Pageable pageable);
    List<CardActivity> findAllCardActivityByCard(Long cardId);
    CardActivity saveCardActivity(CardActivityDto input, UserEntity userEntity, CardEntity cardEntity, CardItemEntity cardItemEntity);
    CardActivity saveCardActivity(CardActivityDto input, UserEntity userEntity, CardEntity cardEntity);
    void updateCardActivity(CardActivityEntity cardActivity, CardActivityDto input,UserEntity userEntity, CardEntity cardEntity, CardItemEntity cardItemEntity);
    void deleteCardActivity(Long id);
}
