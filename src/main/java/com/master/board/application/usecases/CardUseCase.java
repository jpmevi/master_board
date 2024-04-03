package com.master.board.application.usecases;

import com.master.board.application.dao.CaseTypeDAO;
import com.master.board.application.dao.CardDAO;
import com.master.board.application.dto.CardDto;
import com.master.board.domain.models.Card;
import com.master.board.infraestructure.exceptions.BadRequestException;
import com.master.board.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardUseCase {
    private final CardDAO cardDAO;
    private final CaseTypeDAO caseTypeDAO;
    public Page<Card> getAllCards(Pageable pageable){
        return cardDAO.findAllCards(pageable);
    }

    public Optional<Card> getCardById(Long id){
        try{
            var card = cardDAO.find(id);
            if(!card.isPresent()) throw new ResourceNotFoundException("card","id",id);
            return card;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public Card saveCard(CardDto request) {
        var existingCaseType = caseTypeDAO.findById(request.caseTypeId());
        if(!existingCaseType.isPresent()) throw new ResourceNotFoundException("caseType","id",request.caseTypeId());
        return cardDAO.saveCard(request,existingCaseType.get());
    }
    public Card updateCard(Long id, CardDto cardDto) throws ResourceNotFoundException {
        try{
            var existingCard = cardDAO.findById(id);
            var existingCaseType = caseTypeDAO.findById(cardDto.caseTypeId());
            if(!existingCard.isPresent()) throw new ResourceNotFoundException("card","id",id);
            if(!existingCaseType.isPresent()) throw new ResourceNotFoundException("caseType","id",id);
            cardDAO.updateCard(existingCard.get(),cardDto,existingCaseType.get());
            return new Card(existingCard.get());
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public Card deleteCard(Long id) throws ResourceNotFoundException {
        try{
            var existingCard = cardDAO.findById(id);
            if(!existingCard.isPresent()) throw new ResourceNotFoundException("card","id",id);
            cardDAO.deleteCard(id);
            return null;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
