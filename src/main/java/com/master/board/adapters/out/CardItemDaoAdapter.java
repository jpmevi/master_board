package com.master.board.adapters.out;

import com.master.board.adapters.out.entities.CardEntity;
import com.master.board.adapters.out.entities.CardItemEntity;
import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.CaseTypeFlowEntity;
import com.master.board.adapters.out.repositories.CardItemRepository;
import com.master.board.application.dao.CardItemDAO;
import com.master.board.application.dto.CardItemDto;
import com.master.board.domain.models.Card;
import com.master.board.domain.models.CardItem;
import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.CaseTypeFlow;
import com.master.board.domain.models.enums.CardItemState;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CardItemDaoAdapter implements CardItemDAO {
    private final CardItemRepository cardItemRepository;

    @Override
    public Optional<CardItem> find(Long id) {
        return cardItemRepository.findById(id)
                .map(cardItemEntity -> {
                    return new CardItem(
                            cardItemEntity.getId(),
                            cardItemEntity.getHours(),
                            cardItemEntity.getState(),
                            new CaseTypeFlow(cardItemEntity.getCaseTypeFlow()),
                            new Card(cardItemEntity.getCard()),
                            cardItemEntity.getCreatedAt(),
                            cardItemEntity.getUpdatedAt()
                    );
                });
    }

    @Override
    public Optional<CardItemEntity> findById(Long id) {
        return cardItemRepository.findById(id);
    }



    @Override
    public Page<CardItem> findAllCardItem(Pageable pageable) {
        Page<CardItemEntity> cardItemEntitiesPage = cardItemRepository.findAll(pageable);
        List<CardItem> cardItems = cardItemEntitiesPage.getContent().stream()
                .map(cardItemEntity -> {
                    return new CardItem(
                            cardItemEntity.getId(),
                            cardItemEntity.getHours(),
                            cardItemEntity.getState(),
                            new CaseTypeFlow(cardItemEntity.getCaseTypeFlow()),
                            new Card(cardItemEntity.getCard()),
                            cardItemEntity.getCreatedAt(),
                            cardItemEntity.getUpdatedAt()
                    );
                })
                .toList();
        return new PageImpl<>(cardItems, pageable, cardItemEntitiesPage.getTotalElements());
    }

    @Override
    public List<CardItem> findAllCardItemByCard(Long cardId) {
        return  ((List<CardItemEntity>) cardItemRepository.findAllByCard(cardId))
                .stream()
                .map(cardItemEntity -> {
                    return new CardItem(
                            cardItemEntity.getId(),
                            cardItemEntity.getHours(),
                            cardItemEntity.getState(),
                            new CaseTypeFlow(cardItemEntity.getCaseTypeFlow()),
                            new Card(cardItemEntity.getCard()),
                            cardItemEntity.getCreatedAt(),
                            cardItemEntity.getUpdatedAt()
                    );
                }).toList();
    }
    @Override
    public CardItem saveCardItem(CardItemDto request, CaseTypeFlowEntity caseTypeFlowEntity, CardEntity cardEntity) {
        CardItemEntity cardItem = CardItemEntity.builder()
                .hours(request.hours())
                .state(CardItemState.values()[request.state()])
                .caseTypeFlow(caseTypeFlowEntity)
                .card(cardEntity)
                .build();
        cardItemRepository.save(cardItem);

        return new CardItem(cardItem);
    }

    @Override
    public void updateCardItem(CardItemEntity cardItem, CardItemDto request, CaseTypeFlowEntity caseTypeFlowEntity, CardEntity cardEntity) {
        cardItem.setHours(request.hours());
        cardItem.setState(CardItemState.values()[request.state()]);
        cardItem.setCaseTypeFlow(caseTypeFlowEntity);
        cardItem.setCard(cardEntity);
        cardItemRepository.save(cardItem);
    }

    @Override
    public void deleteCardItem(Long id) {
        cardItemRepository.deleteById(id);
    }
}
