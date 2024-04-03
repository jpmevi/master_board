package com.master.board.adapters.out;

import com.master.board.adapters.out.entities.CardUserEntity;
import com.master.board.adapters.out.entities.CardEntity;
import com.master.board.adapters.out.entities.CardItemEntity;
import com.master.board.adapters.out.entities.UserEntity;
import com.master.board.adapters.out.repositories.CardUserRepository;
import com.master.board.application.dao.CardUserDAO;
import com.master.board.application.dto.CardUserDto;
import com.master.board.domain.models.Card;
import com.master.board.domain.models.CardUser;
import com.master.board.domain.models.CardItem;
import com.master.board.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CardUserAdapter implements CardUserDAO {
    private final CardUserRepository cardUserRepository;

    @Override
    public Optional<CardUser> find(Long id) {
        return cardUserRepository.findById(id)
                .map(cardUserEntity -> {
                    return new CardUser(
                            cardUserEntity.getId(),
                            new User(cardUserEntity.getUser()),
                            new Card(cardUserEntity.getCard()),
                            new CardItem(cardUserEntity.getCardItem())
                    );
                });
    }

    @Override
    public Optional<CardUserEntity> findById(Long id) {
        return cardUserRepository.findById(id);
    }



    @Override
    public Page<CardUser> findAllCardUsers(Pageable pageable) {
        Page<CardUserEntity> cardUserEntitiesPage = cardUserRepository.findAll(pageable);
        List<CardUser> cardUsers = cardUserEntitiesPage.getContent().stream()
                .map(cardUserEntity -> {
                    return new CardUser(
                            cardUserEntity.getId(),
                            new User(cardUserEntity.getUser()),
                            new Card(cardUserEntity.getCard()),
                            new CardItem(cardUserEntity.getCardItem())
                    );
                })
                .toList();
        return new PageImpl<>(cardUsers, pageable, cardUserEntitiesPage.getTotalElements());
    }

    @Override
    public List<CardUser> findAllCardUsersByCard(Long cardId) {
        return  ((List<CardUserEntity>) cardUserRepository.findAllByCard(cardId))
                .stream()
                .map(cardUserEntity -> {
                    return new CardUser(
                            cardUserEntity.getId(),
                            new User(cardUserEntity.getUser()),
                            new Card(cardUserEntity.getCard()),
                            new CardItem(cardUserEntity.getCardItem())
                    );
                }).toList();
    }
    @Override
    public CardUser saveCardUser(CardUserDto request, UserEntity userEntity, CardEntity cardEntity, CardItemEntity cardItemEntity) {
        CardUserEntity cardUser = CardUserEntity.builder()
                .user(userEntity)
                .card(cardEntity)
                .cardItem(cardItemEntity)
                .build();
        cardUserRepository.save(cardUser);

        return new CardUser(cardUser);
    }

    @Override
    public CardUser saveCardUser(CardUserDto request, UserEntity userEntity, CardEntity cardEntity) {
        CardUserEntity cardUser = CardUserEntity.builder()
                .user(userEntity)
                .card(cardEntity)
                .build();
        cardUserRepository.save(cardUser);
        return new CardUser(cardUser);
    }

    @Override
    public void updateCardUser(CardUserEntity cardUser, CardUserDto request, UserEntity userEntity, CardEntity cardEntity, CardItemEntity cardItemEntity) {
        cardUser.setUser(userEntity);
        cardUser.setCard(cardEntity);
        cardUser.setCardItem(cardItemEntity);
        cardUserRepository.save(cardUser);
    }

    @Override
    public void deleteCardUser(Long id) {
        cardUserRepository.deleteById(id);
    }
}
