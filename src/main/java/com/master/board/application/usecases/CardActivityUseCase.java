package com.master.board.application.usecases;

import com.master.board.application.dao.CardDAO;
import com.master.board.application.dao.CardActivityDAO;
import com.master.board.application.dao.CardItemDAO;
import com.master.board.application.dao.UserDAO;
import com.master.board.application.dto.CardActivityDto;
import com.master.board.domain.models.CardActivity;
import com.master.board.infraestructure.exceptions.BadRequestException;
import com.master.board.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardActivityUseCase {
    private final CardActivityDAO cardActivityDAO;
    private final CardDAO cardDAO;
    private final UserDAO userDAO;
    private final CardItemDAO cardItemDAO;
    public Page<CardActivity> getAllCardActivitys(Pageable pageable){
        return cardActivityDAO.findAllCardActivity(pageable);
    }

    public Optional<CardActivity> getCardActivityById(Long id){
        try{
            var cardActivity = cardActivityDAO.find(id);
            if(!cardActivity.isPresent()) throw new ResourceNotFoundException("cardActivity","id",id);
            return cardActivity;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public CardActivity saveCardActivity(CardActivityDto request) {
        var existingCard = cardDAO.findById(request.cardId());
        var existingUser = userDAO.find(request.userId());
        var existingCardItem = cardItemDAO.findById(request.cardItemId());
        if(!existingCard.isPresent()) throw new ResourceNotFoundException("card","id",request.cardId());
        if(!existingUser.isPresent()) throw new ResourceNotFoundException("user","id",request.userId());
        if (request.cardItemId() != null) {
            if(!existingCardItem.isPresent()) throw new ResourceNotFoundException("cardItem","id",request.cardItemId());
            return cardActivityDAO.saveCardActivity(request,existingUser.get(),existingCard.get(),existingCardItem.get());
        }
        return cardActivityDAO.saveCardActivity(request,existingUser.get(),existingCard.get());
    }
    public CardActivity updateCardActivity(Long id, CardActivityDto cardActivityDto) throws ResourceNotFoundException {
        try{
            var existingCardActivity = cardActivityDAO.findById(id);
            var existingCard = cardDAO.findById(cardActivityDto.cardId());
            var existingUser = userDAO.find(cardActivityDto.userId());
            var existingCardItem = cardItemDAO.findById(cardActivityDto.cardItemId());
            if(!existingCardActivity.isPresent()) throw new ResourceNotFoundException("cardActivity","id",id);
            if(!existingCard.isPresent()) throw new ResourceNotFoundException("card","id",id);
            if(!existingUser.isPresent()) throw new ResourceNotFoundException("user","id",cardActivityDto.userId());
            if(!existingCardItem.isPresent()) throw new ResourceNotFoundException("cardItem","id",cardActivityDto.cardItemId());
            cardActivityDAO.updateCardActivity(existingCardActivity.get(),cardActivityDto,existingUser.get(),existingCard.get(),existingCardItem.get());
            return new CardActivity(existingCardActivity.get());
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public CardActivity deleteCardActivity(Long id) throws ResourceNotFoundException {
        try{
            var existingCardActivity = cardActivityDAO.findById(id);
            if(!existingCardActivity.isPresent()) throw new ResourceNotFoundException("cardActivity","id",id);
            cardActivityDAO.deleteCardActivity(id);
            return null;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
