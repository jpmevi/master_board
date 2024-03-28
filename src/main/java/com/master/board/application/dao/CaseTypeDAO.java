package com.master.board.application.dao;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.ProjectEntity;
import com.master.board.application.dto.CaseTypeDto;
import com.master.board.domain.models.CaseType;

import java.util.List;
import java.util.Optional;

public interface CaseTypeDAO {
    Optional<CaseTypeEntity> find(Long id);
    List<CaseTypeEntity> findAllCaseTypes();
    CaseTypeEntity saveCaseType(CaseTypeDto input,ProjectEntity project);
    void updateCaseType(CaseTypeEntity caseType, CaseTypeDto input,ProjectEntity project);
    void deleteCaseType(Long id);
}
