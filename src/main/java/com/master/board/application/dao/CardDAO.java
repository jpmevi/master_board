package com.master.board.application.dao;

import com.master.board.adapters.out.entities.CardEntity;
import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.ProjectEntity;
import com.master.board.application.dto.CardDto;
import com.master.board.domain.models.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CardDAO {
    Optional<Card> find(Long id);
    Optional<CardEntity> findById(Long id);
    Page<Card> findAllCards(Pageable pageable);
    List<Card> findAllCardsByCaseType(Long caseTypeId);

    Card saveCard(CardDto input, CaseTypeEntity caseType,ProjectEntity projectEntity);
    void updateCard(CardEntity card, CardDto input,CaseTypeEntity caseType);
    void deleteCard(Long id);
    List<Map<String, Object>> numberOfCardsReport();
    List<Map<String, Object>> hoursAndMoneyByDateRange( String startDate,  String endDate);
}
