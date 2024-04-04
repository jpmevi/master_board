package com.master.board.adapters.in.web;

import com.master.board.application.dto.CardDto;
import com.master.board.application.payload.ApiResponse;
import com.master.board.application.payload.PaginatedResponse;
import com.master.board.application.usecases.CardUseCase;
import com.master.board.domain.models.Card;
import com.master.board.domain.models.Comment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/card")
@RequiredArgsConstructor
@EnableMethodSecurity
public class CardController {
    private final CardUseCase cardUseCase;

    @GetMapping
    public PaginatedResponse<List<Card>> getAllCards(@PageableDefault(page = 0,size = 10) Pageable pageable){
        Page<Card> cardPage = cardUseCase.getAllCards(pageable);
        return new PaginatedResponse<>(HttpStatus.OK.value(),"OK", HttpStatus.OK,cardPage.getContent(),cardPage.getPageable());
    }

    @GetMapping("/numberOfCards")
    public ApiResponse<Object[]> getNumberOfCardsReport(){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,cardUseCase.getNumberOfCardsReport());
    }

    @GetMapping("/hoursAndMoneyByDateRange/{startDate}/{endDate}")
    public ApiResponse<Object[]> hoursAndMoneyByDateRange(@PathVariable String startDate, @PathVariable String endDate){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,cardUseCase.hoursAndMoneyByDateRange(startDate,endDate));
    }
    @GetMapping("/{cardId}")
    public ApiResponse<Card> getCardById(@PathVariable Long cardId){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,cardUseCase.getCardById(cardId));
    }

    @PostMapping
    public ApiResponse<Card> saveCard(@RequestBody @Valid CardDto request)
    {
        return new ApiResponse(HttpStatus.CREATED.value(),"Success", HttpStatus.CREATED,cardUseCase.saveCard(request));
    }

    @PutMapping("/{cardId}")
    public ApiResponse<Card> updateCard(@PathVariable Long cardId, @RequestBody @Valid CardDto cardDto){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,cardUseCase.updateCard(cardId,cardDto));
    }

    @DeleteMapping("/{cardId}")
    public ApiResponse<Null> deleteCard(@PathVariable Long cardId){
        return new ApiResponse(HttpStatus.NO_CONTENT.value(),"Success", HttpStatus.NO_CONTENT,cardUseCase.deleteCard(cardId));
    }
}
