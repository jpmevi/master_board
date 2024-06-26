package com.master.board.adapters.out;

import com.master.board.adapters.out.entities.CardEntity;
import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.ProjectEntity;
import com.master.board.adapters.out.entities.UserEntity;
import com.master.board.adapters.out.repositories.CardRepository;
import com.master.board.application.dao.CardDAO;
import com.master.board.application.dto.CardDto;
import com.master.board.domain.models.Card;
import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.Project;
import com.master.board.domain.models.User;
import com.master.board.domain.models.enums.CardState;
import com.master.board.domain.models.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Component
public class CardDaoAdapter implements CardDAO {
    private final CardRepository cardRepository;

    @Override
    public Optional<Card> find(Long id) {
        return cardRepository.findById(id)
                .map(cardEntity -> {
                    return new Card(
                            cardEntity.getId(),
                            cardEntity.getName(),
                            cardEntity.getDescription(),
                            cardEntity.getDueDate(),
                            cardEntity.getReminderDate(),
                            cardEntity.getIsActive(),
                            cardEntity.getState(),
                            new CaseType(cardEntity.getCaseType()),
                            new Project(cardEntity.getProject()),
                            cardEntity.getCreatedAt(),
                            cardEntity.getUpdatedAt()
                    );
                });
    }

    @Override
    public Optional<CardEntity> findById(Long id) {
        return cardRepository.findById(id);
    }



    @Override
    public Page<Card> findAllCards(Pageable pageable) {
        Page<CardEntity> cardEntitiesPage = cardRepository.findAll(pageable);
        List<Card> cards = cardEntitiesPage.getContent().stream()
                .map(cardEntity -> {
                    return new Card(
                            cardEntity.getId(),
                            cardEntity.getName(),
                            cardEntity.getDescription(),
                            cardEntity.getDueDate(),
                            cardEntity.getReminderDate(),
                            cardEntity.getIsActive(),
                            cardEntity.getState(),
                            new CaseType(cardEntity.getCaseType()),
                            new Project(cardEntity.getProject()),
                            cardEntity.getCreatedAt(),
                            cardEntity.getUpdatedAt()
                    );
                })
                .toList();
        return new PageImpl<>(cards, pageable, cardEntitiesPage.getTotalElements());
    }

    @Override
    public List<Card> findAllCardsByCaseType(Long caseTypeId) {
        return  ((List<CardEntity>) cardRepository.findAllByCaseType(caseTypeId))
                .stream()
                .map(cardEntity -> {
                    return new Card(
                            cardEntity.getId(),
                            cardEntity.getName(),
                            cardEntity.getDescription(),
                            cardEntity.getDueDate(),
                            cardEntity.getReminderDate(),
                            cardEntity.getIsActive(),
                            cardEntity.getState(),
                            new CaseType(cardEntity.getCaseType()),
                            new Project(cardEntity.getProject()),
                            cardEntity.getCreatedAt(),
                            cardEntity.getUpdatedAt()
                    );
                }).toList();
    }

    @Override
    public List<Card> findAllCardsByProject(Long projectId) {
        return  ((List<CardEntity>) cardRepository.findAllByProject(projectId))
                .stream()
                .map(cardEntity -> {
                    return new Card(
                            cardEntity.getId(),
                            cardEntity.getName(),
                            cardEntity.getDescription(),
                            cardEntity.getDueDate(),
                            cardEntity.getReminderDate(),
                            cardEntity.getIsActive(),
                            cardEntity.getState(),
                            new CaseType(cardEntity.getCaseType()),
                            new Project(cardEntity.getProject()),
                            cardEntity.getCreatedAt(),
                            cardEntity.getUpdatedAt()
                    );
                }).toList();
    }
    @Override
    public Card saveCard(CardDto request, CaseTypeEntity caseType, ProjectEntity projectEntity) {
        CardEntity card = CardEntity.builder()
                .name(request.name())
                .description(request.description())
                .dueDate(request.dueDate())
                .reminderDate(request.reminderDate())
                .isActive(request.isActive())
                .state(CardState.values()[request.state()])
                .caseType(caseType)
                .project(projectEntity)
                .build();
        cardRepository.save(card);

        return new Card(card);
    }

    @Override
    public void updateCard(CardEntity card, CardDto request, CaseTypeEntity caseType) {
        card.setName(request.name());
        card.setDescription(request.description());
        card.setDueDate(request.dueDate());
        card.setReminderDate(request.reminderDate());
        card.setIsActive(request.isActive());
        card.setState(CardState.values()[request.state()]);
        card.setCaseType(caseType);
        cardRepository.save(card);
    }

    @Override
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public List<Map<String, Object>> numberOfCardsReport() {
        List<Object[]> reportData = cardRepository.numberOfCardsReport();
        List<Map<String, Object>> formattedData = new ArrayList<>();

        for (Object[] row : reportData) {
            Map<String, Object> projectInfo = new HashMap<>();
            projectInfo.put("project", row[0]);
            projectInfo.put("numberOfCases", row[1]);
            formattedData.add(projectInfo);
        }

        return formattedData;
    }

    @Override
    public List<Map<String, Object>> hoursAndMoneyByDateRange(String startDate, String endDate) {
        List<Object[]> data = cardRepository.hoursAndMoneyByDateRange(startDate, endDate);

        List<Map<String, Object>> formattedData = new ArrayList<>();

        for (Object[] row : data) {
            Map<String, Object> rowData = new HashMap<>();
            rowData.put("date", row[0]);
            rowData.put("total_hours", row[1]);
            rowData.put("total_money_invested", row[2]);
            formattedData.add(rowData);
        }
        return formattedData;
    }


}
