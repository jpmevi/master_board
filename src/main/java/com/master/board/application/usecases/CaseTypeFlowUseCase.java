package com.master.board.application.usecases;

import com.master.board.application.dao.CaseTypeDAO;
import com.master.board.application.dao.CaseTypeFlowDAO;
import com.master.board.application.dto.CaseTypeFlowDto;
import com.master.board.application.dto.RegisterDto;
import com.master.board.application.payload.AuthResponse;
import com.master.board.domain.models.CaseTypeFlow;
import com.master.board.infraestructure.exceptions.BadRequestException;
import com.master.board.infraestructure.exceptions.ResourceAlreadyExistsException;
import com.master.board.infraestructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CaseTypeFlowUseCase {
    private final CaseTypeFlowDAO caseTypeFlowDAO;
    private final CaseTypeDAO caseTypeDAO;
    public List<CaseTypeFlow> getAllCaseTypeFlows(){
        return caseTypeFlowDAO.findAllCaseTypeFlows();
    }

    public Optional<CaseTypeFlow> getCaseTypeFlowById(Long id){
        try{
            var caseTypeFlow = caseTypeFlowDAO.find(id);
            if(!caseTypeFlow.isPresent()) throw new ResourceNotFoundException("caseTypeFlow","id",id);
            return caseTypeFlow;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public CaseTypeFlow saveCaseTypeFlow(CaseTypeFlowDto request) {
        var existingCaseType = caseTypeDAO.findById(request.caseTypeId());
        if(!existingCaseType.isPresent()) throw new ResourceNotFoundException("caseType","id",request.caseTypeId());
        return caseTypeFlowDAO.saveCaseTypeFlow(request,existingCaseType.get());
    }
    public CaseTypeFlow updateCaseTypeFlow(Long id, CaseTypeFlowDto caseTypeFlowDto) throws ResourceNotFoundException {
        try{
            var existingCaseTypeFlow = caseTypeFlowDAO.findById(id);
            var existingCaseType = caseTypeDAO.findById(caseTypeFlowDto.caseTypeId());
            if(!existingCaseTypeFlow.isPresent()) throw new ResourceNotFoundException("caseTypeFlow","id",id);
            if(!existingCaseType.isPresent()) throw new ResourceNotFoundException("caseType","id",id);
            caseTypeFlowDAO.updateCaseTypeFlow(existingCaseTypeFlow.get(),caseTypeFlowDto,existingCaseType.get());
            return new CaseTypeFlow(existingCaseTypeFlow.get());
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public CaseTypeFlow deleteCaseTypeFlow(Long id) throws ResourceNotFoundException {
        try{
            var existingCaseTypeFlow = caseTypeFlowDAO.findById(id);
            if(!existingCaseTypeFlow.isPresent()) throw new ResourceNotFoundException("caseTypeFlow","id",id);
            caseTypeFlowDAO.deleteCaseTypeFlow(id);
            return null;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
