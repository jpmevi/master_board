package com.master.board.application.dao;

import com.master.board.adapters.out.entities.*;
import com.master.board.adapters.out.entities.CardItemEntity;
import com.master.board.application.dto.CardItemDto;
import com.master.board.domain.models.CardItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CardItemDAO {
    Optional<CardItem> find(Long id);
    Optional<CardItemEntity> findById(Long id);
    Page<CardItem> findAllCardItem(Pageable pageable);
    List<CardItem> findAllCardItemByCard(Long cardId);
    CardItem saveCardItem(CardItemDto input, CaseTypeFlowEntity caseTypeFlowEntity, CardEntity cardEntity);
    void updateCardItem(CardItemEntity cardItem, CardItemDto input,CaseTypeFlowEntity caseTypeFlowEntity, CardEntity cardEntity);
    void deleteCardItem(Long id);
}
