package com.master.board.adapters.out;

import com.master.board.adapters.out.entities.*;
import com.master.board.adapters.out.repositories.CardActivityRepository;
import com.master.board.application.dao.CardActivityDAO;
import com.master.board.application.dto.CardActivityDto;
import com.master.board.domain.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CardActivityAdapter implements CardActivityDAO {
    private final CardActivityRepository cardActivityRepository;

    @Override
    public Optional<CardActivity> find(Long id) {
        return cardActivityRepository.findById(id)
                .map(cardActivityEntity -> {
                    return new CardActivity(
                            cardActivityEntity.getId(),
                            new User(cardActivityEntity.getUser()),
                            new Card(cardActivityEntity.getCard()),
                            new CardItem(cardActivityEntity.getCardItem()),
                            cardActivityEntity.getActivity(),
                            cardActivityEntity.getCreatedAt()
                    );
                });
    }

    @Override
    public Optional<CardActivityEntity> findById(Long id) {
        return cardActivityRepository.findById(id);
    }



    @Override
    public Page<CardActivity> findAllCardActivity(Pageable pageable) {
        Page<CardActivityEntity> cardActivityEntitiesPage = cardActivityRepository.findAll(pageable);
        List<CardActivity> cardActivitys = cardActivityEntitiesPage.getContent().stream()
                .map(cardActivityEntity -> {
                    return new CardActivity(
                            cardActivityEntity.getId(),
                            new User(cardActivityEntity.getUser()),
                            new Card(cardActivityEntity.getCard()),
                            new CardItem(cardActivityEntity.getCardItem()),
                            cardActivityEntity.getActivity(),
                            cardActivityEntity.getCreatedAt()
                    );
                })
                .toList();
        return new PageImpl<>(cardActivitys, pageable, cardActivityEntitiesPage.getTotalElements());
    }

    @Override
    public List<CardActivity> findAllCardActivityByCard(Long cardId) {
        return  ((List<CardActivityEntity>) cardActivityRepository.findAllByCard(cardId))
                .stream()
                .map(cardActivityEntity -> {
                    return new CardActivity(
                            cardActivityEntity.getId(),
                            new User(cardActivityEntity.getUser()),
                            new Card(cardActivityEntity.getCard()),
                            new CardItem(cardActivityEntity.getCardItem()),
                            cardActivityEntity.getActivity(),
                            cardActivityEntity.getCreatedAt()
                    );
                }).toList();
    }
    @Override
    public CardActivity saveCardActivity(CardActivityDto request, UserEntity userEntity, CardEntity cardEntity, CardItemEntity cardItemEntity) {
        CardActivityEntity cardActivity = CardActivityEntity.builder()
                .user(userEntity)
                .card(cardEntity)
                .cardItem(cardItemEntity)
                .activity(request.activity())
                .build();
        cardActivityRepository.save(cardActivity);

        return new CardActivity(cardActivity);
    }

    @Override
    public CardActivity saveCardActivity(CardActivityDto request, UserEntity userEntity, CardEntity cardEntity) {
        CardActivityEntity cardActivity = CardActivityEntity.builder()
                .user(userEntity)
                .card(cardEntity)
                .activity(request.activity())
                .build();
        cardActivityRepository.save(cardActivity);
        return new CardActivity(cardActivity);
    }

    @Override
    public void updateCardActivity(CardActivityEntity cardActivity, CardActivityDto request, UserEntity userEntity, CardEntity cardEntity, CardItemEntity cardItemEntity) {
        cardActivity.setUser(userEntity);
        cardActivity.setCard(cardEntity);
        cardActivity.setCardItem(cardItemEntity);
        cardActivity.setActivity(request.activity());
        cardActivityRepository.save(cardActivity);
    }

    @Override
    public void deleteCardActivity(Long id) {
        cardActivityRepository.deleteById(id);
    }
}
