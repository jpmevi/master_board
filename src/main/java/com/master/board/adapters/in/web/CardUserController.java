package com.master.board.adapters.in.web;

import com.master.board.application.dto.CardUserDto;
import com.master.board.application.payload.ApiResponse;
import com.master.board.application.payload.PaginatedResponse;
import com.master.board.application.usecases.CardUserUseCase;
import com.master.board.domain.models.CardUser;
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
@RequestMapping("api/v1/cardUser")
@RequiredArgsConstructor
@EnableMethodSecurity
public class CardUserController {
    private final CardUserUseCase cardUserUseCase;

    @GetMapping
    public PaginatedResponse<List<CardUser>> getAllCardUsers(@PageableDefault(page = 0,size = 10) Pageable pageable){
        Page<CardUser> cardUserPage = cardUserUseCase.getAllCardUsers(pageable);
        return new PaginatedResponse<>(HttpStatus.OK.value(),"OK", HttpStatus.OK,cardUserPage.getContent(),cardUserPage.getPageable());
    }

    @GetMapping("/{cardUserId}")
    public ApiResponse<CardUser> getCardUserById(@PathVariable Long cardUserId){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,cardUserUseCase.getCardUserById(cardUserId));
    }

    @PostMapping
    public ApiResponse<CardUser> saveCardUser(@RequestBody @Valid CardUserDto request)
    {
        return new ApiResponse(HttpStatus.CREATED.value(),"Success", HttpStatus.CREATED,cardUserUseCase.saveCardUser(request));
    }

    @PutMapping("/{cardUserId}")
    public ApiResponse<CardUser> updateCardUser(@PathVariable Long cardUserId, @RequestBody @Valid CardUserDto cardUserDto){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,cardUserUseCase.updateCardUser(cardUserId,cardUserDto));
    }

    @DeleteMapping("/{cardUserId}")
    public ApiResponse<Null> deleteCardUser(@PathVariable Long cardUserId){
        return new ApiResponse(HttpStatus.NO_CONTENT.value(),"Success", HttpStatus.NO_CONTENT,cardUserUseCase.deleteCardUser(cardUserId));
    }
}
