package com.master.board.adapters.out;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.ProjectEntity;
import com.master.board.adapters.out.repositories.CaseTypeRepository;
import com.master.board.application.dao.CaseTypeDAO;
import com.master.board.application.dto.CaseTypeDto;
import com.master.board.domain.models.CaseType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CaseTypeDaoAdapter implements CaseTypeDAO {
    private final CaseTypeRepository caseTypeRepository;

    @Override
    public Optional<CaseTypeEntity> find(Long id) {
        return caseTypeRepository.findById(id);
    }

    @Override
    public List<CaseTypeEntity> findAllCaseTypes() {
        return   caseTypeRepository.findAll();
    }

    @Override
    public CaseTypeEntity saveCaseType(CaseTypeDto request, ProjectEntity project) {
        CaseTypeEntity caseType = CaseTypeEntity.builder()
                .name(request.name())
                .description(request.description())
                .project(project)
                .labelColor(request.labelColor())
                .build();
        caseTypeRepository.save(caseType);

        return caseType;
    }

    @Override
    public void updateCaseType(CaseTypeEntity caseType, CaseTypeDto input, ProjectEntity project) {

        caseType.setName(input.name());
        caseType.setDescription(input.description());
        caseType.setProject(project);
        caseType.setLabelColor(input.labelColor());
        caseTypeRepository.save(caseType);
    }

    @Override
    public void deleteCaseType(Long id) {
        caseTypeRepository.deleteById(id);
    }
}
