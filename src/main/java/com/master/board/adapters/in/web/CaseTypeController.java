package com.master.board.adapters.in.web;

import com.master.board.application.dto.RegisterDto;
import com.master.board.application.usecases.CaseTypeUseCase;
import com.master.board.application.usecases.UserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/caseType/v1")
@RequiredArgsConstructor
@EnableMethodSecurity
public class CaseTypeController {
    private final CaseTypeUseCase caseTypeUseCase;

    @GetMapping
    public ResponseEntity<?> getAllCaseTypes(){
        return ResponseEntity.ok(caseTypeUseCase.getAllCaseTypes());
    }

    @GetMapping("/{caseTypeId}")
    public ResponseEntity<?> getCaseTypeById(@PathVariable Long caseTypeId){
        return ResponseEntity.status(HttpStatus.OK).body(caseTypeUseCase.getCaseTypeById(caseTypeId));
    }
}
