package com.master.board.application.dao;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.CaseTypeFlowEntity;
import com.master.board.adapters.out.entities.ProjectEntity;
import com.master.board.application.dto.CaseTypeDto;
import com.master.board.application.dto.CaseTypeFlowDto;
import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.CaseTypeFlow;

import java.util.List;
import java.util.Optional;

public interface CaseTypeFlowDAO {
    Optional<CaseTypeFlow> find(Long id);
    Optional<CaseTypeFlowEntity> findById(Long id);
    List<CaseTypeFlow> findAllCaseTypeFlows();
    CaseTypeFlow saveCaseTypeFlow(CaseTypeFlowDto input,CaseTypeEntity caseType);
    void updateCaseTypeFlow(CaseTypeFlowEntity caseTypeFlow, CaseTypeFlowDto input, CaseTypeEntity caseType);
    void deleteCaseTypeFlow(Long id);
}
