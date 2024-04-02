package com.master.board.application.dao;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.ProjectEntity;
import com.master.board.application.dto.CaseTypeDto;
import com.master.board.domain.models.CaseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CaseTypeDAO {
    Optional<CaseType> find(Long id);
    Optional<CaseTypeEntity> findById(Long id);
    List<CaseType> findByName(String caseTypeName);
    Page<CaseType> findAllCaseTypes(Pageable pageable);
    List<CaseType> findAllCaseTypesByProject(Long projectId);
    CaseTypeEntity saveCaseType(CaseTypeDto input,ProjectEntity project);
    void updateCaseType(CaseTypeEntity caseType, CaseTypeDto input,ProjectEntity project);
    void deleteCaseType(Long id);
}
