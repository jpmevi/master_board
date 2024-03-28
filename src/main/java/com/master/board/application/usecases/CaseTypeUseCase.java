package com.master.board.application.usecases;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.application.dao.CaseTypeDAO;
import com.master.board.application.dao.UserDAO;
import com.master.board.application.dto.CaseTypeDto;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.AuthResponse;
import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.User;
import com.master.board.infraestructure.exceptions.BadRequestException;
import com.master.board.infraestructure.exceptions.ResourceAlreadyExistsException;
import com.master.board.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CaseTypeUseCase {
    private final CaseTypeDAO caseTypeDAO;
    public List<CaseTypeEntity> getAllCaseTypes(){
        return caseTypeDAO.findAllCaseTypes();
    }

    public ResponseEntity<?> getCaseTypeById(Long id){
        try{
            var caseType = caseTypeDAO.find(id);
            if(!caseType.isPresent()) throw new ResourceNotFoundException("caseType","id",id);
            return new ResponseEntity<>( caseType,HttpStatus.OK);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
