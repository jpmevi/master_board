package com.master.board.adapters.in.web;

import com.master.board.application.dto.CaseTypeDto;
import com.master.board.application.dto.CaseTypeFlowDto;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.ApiResponse;
import com.master.board.application.payload.PaginatedResponse;
import com.master.board.application.usecases.CaseTypeUseCase;
import com.master.board.application.usecases.UserUseCase;
import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.CaseTypeFlow;
import com.master.board.domain.models.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@CrossOrigin("http://localhost:4200")
public class CaseTypeController {
    private final CaseTypeUseCase caseTypeUseCase;

    @GetMapping
    public PaginatedResponse<List<CaseType>> getAllCaseTypes(@PageableDefault(page = 0,size = 10) Pageable pageable){
        Page<CaseType> caseTypePage = caseTypeUseCase.getAllCaseTypes(pageable);
        return new PaginatedResponse<>(HttpStatus.OK.value(),"OK", HttpStatus.OK,caseTypePage.getContent(),caseTypePage.getPageable());
    }

    /**
     * DISABLED TEMPORALLY
     * @param projectId
     * @return
     */
    /**
     * @GetMapping("caseTypeByProject/{projectId}")
     *     public ApiResponse<List<CaseType>> getAllCaseTypesByProject(@PathVariable Long projectId){
     *         return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,caseTypeUseCase.getAllCaseTypesByProject(projectId));
     *     }
     *
     */

    @GetMapping("/{caseTypeId}")
    public ApiResponse<CaseType> getCaseTypeById(@PathVariable Long caseTypeId){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,caseTypeUseCase.getCaseTypeById(caseTypeId));
    }

    @GetMapping("/caseTypeByName/{caseTypeName}")
    public ApiResponse<List<CaseType>> getCaseTypeByName(@PathVariable String caseTypeName){
        return new ApiResponse(HttpStatus.OK.value(),"Success", HttpStatus.OK,caseTypeUseCase.getCaseTypeByName(caseTypeName));
    }

    @PostMapping
    public ApiResponse<CaseType> saveCaseType(@RequestBody @Valid CaseTypeDto request)
    {
        return new ApiResponse(HttpStatus.CREATED.value(),"Success", HttpStatus.CREATED,caseTypeUseCase.saveCaseType(request));
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
