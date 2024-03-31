package com.master.board.adapters.in.web;

import com.master.board.application.dto.CaseTypeDto;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.ApiResponse;
import com.master.board.application.usecases.CaseTypeUseCase;
import com.master.board.application.usecases.UserUseCase;
import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/caseType")
@RequiredArgsConstructor
@EnableMethodSecurity
public class CaseTypeController {
    private final CaseTypeUseCase caseTypeUseCase;

    @GetMapping
    public ApiResponse<List<CaseType>> getAllCaseTypes(){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,caseTypeUseCase.getAllCaseTypes());
    }

    @GetMapping("/{caseTypeId}")
    public ApiResponse<CaseType> getCaseTypeById(@PathVariable Long caseTypeId){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,caseTypeUseCase.getCaseTypeById(caseTypeId));
    }

    @PutMapping("/{caseTypeId}")
    public ApiResponse<CaseType> updateCaseType(@PathVariable Long caseTypeId, @RequestBody @Valid CaseTypeDto caseTypeDto){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,caseTypeUseCase.updateCaseType(caseTypeId,caseTypeDto));
    }

    @DeleteMapping("/{caseTypeId}")
    public ApiResponse<Null> deleteCaseType(@PathVariable Long caseTypeId){
        return new ApiResponse(HttpStatus.NO_CONTENT.value(),"Success", HttpStatus.NO_CONTENT,caseTypeUseCase.deleteCaseType(caseTypeId));
    }
}
