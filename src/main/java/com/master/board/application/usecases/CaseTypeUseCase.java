package com.master.board.application.usecases;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.application.dao.CaseTypeDAO;
import com.master.board.application.dao.ProjectDAO;
import com.master.board.application.dao.UserDAO;
import com.master.board.application.dto.CaseTypeDto;
import com.master.board.application.dto.CaseTypeFlowDto;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.AuthResponse;
import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.CaseTypeFlow;
import com.master.board.domain.models.User;
import com.master.board.infraestructure.exceptions.BadRequestException;
import com.master.board.infraestructure.exceptions.ResourceAlreadyExistsException;
import com.master.board.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CaseTypeUseCase {
    private final CaseTypeDAO caseTypeDAO;
    public Page<CaseType> getAllCaseTypes(Pageable pageable){
        return caseTypeDAO.findAllCaseTypes(pageable);
    }

    public List<CaseType> getAllCaseTypesByProject(Long projectId){
        return caseTypeDAO.findAllCaseTypesByProject(projectId);
    }

    public Optional<CaseType> getCaseTypeById(Long id){
        try{
            var caseType = caseTypeDAO.find(id);
            if(!caseType.isPresent()) throw new ResourceNotFoundException("caseType","id",id);
            return caseType;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<CaseType> getCaseTypeByName(String caseTypeName){
        try{
            return caseTypeDAO.findByName(caseTypeName);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public CaseType saveCaseType(CaseTypeDto request) {
        return new CaseType(caseTypeDAO.saveCaseType(request));
    }

    public CaseType updateCaseType(Long id, CaseTypeDto caseTypeDto) throws ResourceNotFoundException {
        try{
            var existingCaseType = caseTypeDAO.findById(id);
            if(!existingCaseType.isPresent()) throw new ResourceNotFoundException("caseType","id",id);
            caseTypeDAO.updateCaseType(existingCaseType.get(),caseTypeDto);
            return new CaseType(existingCaseType.get());
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteCaseType(Long id) throws ResourceNotFoundException {
        try{
            var existingCaseType = caseTypeDAO.findById(id);
            if(!existingCaseType.isPresent()) throw new ResourceNotFoundException("caseType","id",id);
            caseTypeDAO.deleteCaseType(id);
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
