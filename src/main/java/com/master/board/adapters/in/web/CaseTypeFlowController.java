package com.master.board.adapters.in.web;

import com.master.board.application.dto.CaseTypeFlowDto;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.ApiResponse;
import com.master.board.application.payload.AuthResponse;
import com.master.board.application.usecases.CaseTypeFlowUseCase;
import com.master.board.domain.models.CaseTypeFlow;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/caseTypeFlow")
@RequiredArgsConstructor
@EnableMethodSecurity
@CrossOrigin("http://localhost:4200")
public class CaseTypeFlowController {
    private final CaseTypeFlowUseCase caseTypeFlowUseCase;

    @GetMapping
    public ApiResponse<List<CaseTypeFlow>> getAllCaseTypeFlows(){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,caseTypeFlowUseCase.getAllCaseTypeFlows());
    }

    @GetMapping("/{caseTypeFlowId}")
    public ApiResponse<CaseTypeFlow> getCaseTypeFlowById(@PathVariable Long caseTypeFlowId){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,caseTypeFlowUseCase.getCaseTypeFlowById(caseTypeFlowId));
    }

    @PostMapping
    public ApiResponse<CaseTypeFlow> saveCaseTypeFlow(@RequestBody @Valid CaseTypeFlowDto request)
    {
        return new ApiResponse(HttpStatus.CREATED.value(),"Success", HttpStatus.CREATED,caseTypeFlowUseCase.saveCaseTypeFlow(request));
    }

    @PutMapping("/{caseTypeFlowId}")
    public ApiResponse<CaseTypeFlow> updateCaseTypeFlow(@PathVariable Long caseTypeFlowId, @RequestBody @Valid CaseTypeFlowDto caseTypeFlowDto){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,caseTypeFlowUseCase.updateCaseTypeFlow(caseTypeFlowId,caseTypeFlowDto));
    }

    @DeleteMapping("/{caseTypeFlowId}")
    public ApiResponse<Null> deleteCaseTypeFlow(@PathVariable Long caseTypeFlowId){
        return new ApiResponse(HttpStatus.NO_CONTENT.value(),"Success", HttpStatus.NO_CONTENT,caseTypeFlowUseCase.deleteCaseTypeFlow(caseTypeFlowId));
    }
}
