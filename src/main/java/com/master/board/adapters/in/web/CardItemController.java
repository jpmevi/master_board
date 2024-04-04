package com.master.board.adapters.in.web;

import com.master.board.application.dto.CardItemDto;
import com.master.board.application.payload.ApiResponse;
import com.master.board.application.payload.PaginatedResponse;
import com.master.board.application.usecases.CardItemUseCase;
import com.master.board.domain.models.CardItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cardItem")
@RequiredArgsConstructor
@EnableMethodSecurity
@CrossOrigin("http://localhost:4200")
public class CardItemController {
    private final CardItemUseCase cardItemUseCase;

    @GetMapping
    public PaginatedResponse<List<CardItem>> getAllCardItems(@PageableDefault(page = 0,size = 10) Pageable pageable){
        Page<CardItem> cardItemPage = cardItemUseCase.getAllCardItems(pageable);
        return new PaginatedResponse<>(HttpStatus.OK.value(),"OK", HttpStatus.OK,cardItemPage.getContent(),cardItemPage.getPageable());
    }

    @GetMapping("/{cardItemId}")
    public ApiResponse<CardItem> getCardItemById(@PathVariable Long cardItemId){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,cardItemUseCase.getCardItemById(cardItemId));
    }

    @PostMapping
    public ApiResponse<CardItem> saveCardItem(@RequestBody @Valid CardItemDto request)
    {
        return new ApiResponse(HttpStatus.CREATED.value(),"Success", HttpStatus.CREATED,cardItemUseCase.saveCardItem(request));
    }

    @PutMapping("/{cardItemId}")
    public ApiResponse<CardItem> updateCardItem(@PathVariable Long cardItemId, @RequestBody @Valid CardItemDto cardItemDto){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,cardItemUseCase.updateCardItem(cardItemId,cardItemDto));
    }

    @DeleteMapping("/{cardItemId}")
    public ApiResponse<Null> deleteCardItem(@PathVariable Long cardItemId){
        return new ApiResponse(HttpStatus.NO_CONTENT.value(),"Success", HttpStatus.NO_CONTENT,cardItemUseCase.deleteCardItem(cardItemId));
    }
}
