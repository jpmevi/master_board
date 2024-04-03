package com.master.board.application.usecases;

import com.master.board.application.dao.CardItemDAO;
import com.master.board.application.dao.CardDAO;
import com.master.board.application.dao.CaseTypeFlowDAO;
import com.master.board.application.dto.CardItemDto;
import com.master.board.domain.models.CardItem;
import com.master.board.infraestructure.exceptions.BadRequestException;
import com.master.board.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardItemUseCase {
    private final CardItemDAO cardItemDAO;
    private final CardDAO cardDAO;
    private final CaseTypeFlowDAO caseTypeFlowDAO;
    public Page<CardItem> getAllCardItems(Pageable pageable){
        return cardItemDAO.findAllCardItem(pageable);
    }

    public Optional<CardItem> getCardItemById(Long id){
        try{
            var cardItem = cardItemDAO.find(id);
            if(!cardItem.isPresent()) throw new ResourceNotFoundException("cardItem","id",id);
            return cardItem;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public CardItem saveCardItem(CardItemDto request) {
        var existingCard = cardDAO.findById(request.cardId());
        var existingCaseTypeFlow = caseTypeFlowDAO.findById(request.caseTypeFlowId());
        if(!existingCard.isPresent()) throw new ResourceNotFoundException("card","id",request.cardId());
        if(!existingCaseTypeFlow.isPresent()) throw new ResourceNotFoundException("caseTypeFlow","id",request.caseTypeFlowId());
        return cardItemDAO.saveCardItem(request,existingCaseTypeFlow.get(),existingCard.get());
    }
    public CardItem updateCardItem(Long id, CardItemDto cardItemDto) throws ResourceNotFoundException {
        try{
            var existingCardItem = cardItemDAO.findById(id);
            var existingCard = cardDAO.findById(cardItemDto.cardId());
            var existingCaseTypeFlow = caseTypeFlowDAO.findById(cardItemDto.caseTypeFlowId());
            if(!existingCardItem.isPresent()) throw new ResourceNotFoundException("cardItem","id",id);
            if(!existingCard.isPresent()) throw new ResourceNotFoundException("card","id",id);
            if(!existingCaseTypeFlow.isPresent()) throw new ResourceNotFoundException("caseTypeFlow","id",cardItemDto.caseTypeFlowId());
            cardItemDAO.updateCardItem(existingCardItem.get(),cardItemDto,existingCaseTypeFlow.get(),existingCard.get());
            return new CardItem(existingCardItem.get());
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public CardItem deleteCardItem(Long id) throws ResourceNotFoundException {
        try{
            var existingCardItem = cardItemDAO.findById(id);
            if(!existingCardItem.isPresent()) throw new ResourceNotFoundException("cardItem","id",id);
            cardItemDAO.deleteCardItem(id);
            return null;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
