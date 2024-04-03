package com.master.board.adapters.in.web;

import com.master.board.application.dto.CardActivityDto;
import com.master.board.application.payload.ApiResponse;
import com.master.board.application.payload.PaginatedResponse;
import com.master.board.application.usecases.CardActivityUseCase;
import com.master.board.domain.models.CardActivity;
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
@RequestMapping("api/v1/cardActivity")
@RequiredArgsConstructor
@EnableMethodSecurity
public class CardActivityController {
    private final CardActivityUseCase cardActivityUseCase;

    @GetMapping
    public PaginatedResponse<List<CardActivity>> getAllCardActivitys(@PageableDefault(page = 0,size = 10) Pageable pageable){
        Page<CardActivity> cardActivityPage = cardActivityUseCase.getAllCardActivitys(pageable);
        return new PaginatedResponse<>(HttpStatus.OK.value(),"OK", HttpStatus.OK,cardActivityPage.getContent(),cardActivityPage.getPageable());
    }

    @GetMapping("/{cardActivityId}")
    public ApiResponse<CardActivity> getCardActivityById(@PathVariable Long cardActivityId){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,cardActivityUseCase.getCardActivityById(cardActivityId));
    }

    @PostMapping
    public ApiResponse<CardActivity> saveCardActivity(@RequestBody @Valid CardActivityDto request)
    {
        return new ApiResponse(HttpStatus.CREATED.value(),"Success", HttpStatus.CREATED,cardActivityUseCase.saveCardActivity(request));
    }

    @PutMapping("/{cardActivityId}")
    public ApiResponse<CardActivity> updateCardActivity(@PathVariable Long cardActivityId, @RequestBody @Valid CardActivityDto cardActivityDto){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,cardActivityUseCase.updateCardActivity(cardActivityId,cardActivityDto));
    }

    @DeleteMapping("/{cardActivityId}")
    public ApiResponse<Null> deleteCardActivity(@PathVariable Long cardActivityId){
        return new ApiResponse(HttpStatus.NO_CONTENT.value(),"Success", HttpStatus.NO_CONTENT,cardActivityUseCase.deleteCardActivity(cardActivityId));
    }
}
