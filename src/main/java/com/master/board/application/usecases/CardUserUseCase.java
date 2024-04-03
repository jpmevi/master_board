package com.master.board.application.usecases;

import com.master.board.application.dao.CardUserDAO;
import com.master.board.application.dao.CardDAO;
import com.master.board.application.dao.CardItemDAO;
import com.master.board.application.dao.UserDAO;
import com.master.board.application.dto.CardUserDto;
import com.master.board.domain.models.CardUser;
import com.master.board.infraestructure.exceptions.BadRequestException;
import com.master.board.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardUserUseCase {
    private final CardUserDAO cardUserDAO;
    private final CardDAO cardDAO;
    private final UserDAO userDAO;
    private final CardItemDAO cardItemDAO;
    public Page<CardUser> getAllCardUsers(Pageable pageable){
        return cardUserDAO.findAllCardUsers(pageable);
    }

    public Optional<CardUser> getCardUserById(Long id){
        try{
            var cardUser = cardUserDAO.find(id);
            if(!cardUser.isPresent()) throw new ResourceNotFoundException("cardUser","id",id);
            return cardUser;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public CardUser saveCardUser(CardUserDto request) {
        var existingCard = cardDAO.findById(request.cardId());
        var existingUser = userDAO.find(request.userId());
        var existingCardItem = cardItemDAO.findById(request.cardItemId());
        if(!existingCard.isPresent()) throw new ResourceNotFoundException("card","id",request.cardId());
        if(!existingUser.isPresent()) throw new ResourceNotFoundException("user","id",request.userId());
        if (request.cardItemId() != null) {
            if(!existingCardItem.isPresent()) throw new ResourceNotFoundException("cardItem","id",request.cardItemId());
            return cardUserDAO.saveCardUser(request,existingUser.get(),existingCard.get(),existingCardItem.get());
        }
        return cardUserDAO.saveCardUser(request,existingUser.get(),existingCard.get());
    }
    public CardUser updateCardUser(Long id, CardUserDto cardUserDto) throws ResourceNotFoundException {
        try{
            var existingCardUser = cardUserDAO.findById(id);
            var existingCard = cardDAO.findById(cardUserDto.cardId());
            var existingUser = userDAO.find(cardUserDto.userId());
            var existingCardItem = cardItemDAO.findById(cardUserDto.cardItemId());
            if(!existingCardUser.isPresent()) throw new ResourceNotFoundException("cardUser","id",id);
            if(!existingCard.isPresent()) throw new ResourceNotFoundException("card","id",id);
            if(!existingUser.isPresent()) throw new ResourceNotFoundException("user","id",cardUserDto.userId());
            if(!existingCardItem.isPresent()) throw new ResourceNotFoundException("cardItem","id",cardUserDto.cardItemId());
            cardUserDAO.updateCardUser(existingCardUser.get(),cardUserDto,existingUser.get(),existingCard.get(),existingCardItem.get());
            return new CardUser(existingCardUser.get());
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public CardUser deleteCardUser(Long id) throws ResourceNotFoundException {
        try{
            var existingCardUser = cardUserDAO.findById(id);
            if(!existingCardUser.isPresent()) throw new ResourceNotFoundException("cardUser","id",id);
            cardUserDAO.deleteCardUser(id);
            return null;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
