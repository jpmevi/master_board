package com.master.board.adapters.out;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.CaseTypeFlowEntity;
import com.master.board.adapters.out.entities.ProjectEntity;
import com.master.board.adapters.out.repositories.CaseTypeFlowRepository;
import com.master.board.application.dao.CaseTypeFlowDAO;
import com.master.board.application.dto.CaseTypeFlowDto;
import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.CaseTypeFlow;
import com.master.board.domain.models.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CaseTypeFlowDaoAdapter implements CaseTypeFlowDAO {
    private final CaseTypeFlowRepository caseTypeFlowRepository;

    @Override
    public Optional<CaseTypeFlow> find(Long id) {
        return caseTypeFlowRepository.findById(id)
                .map(caseTypeFlowEntity -> {
                    CaseType caseType = new CaseType(caseTypeFlowEntity.getCaseType());
                    return new CaseTypeFlow(
                            caseTypeFlowEntity.getId(),
                            caseTypeFlowEntity.getStage(),
                            caseTypeFlowEntity.getOrder(),
                            caseType
                    );
                });
    }

    @Override
    public Optional<CaseTypeFlowEntity> findById(Long id) {
        return caseTypeFlowRepository.findById(id);
    }

    @Override
    public List<CaseTypeFlow> findAllCaseTypeFlows() {
        return  ((List<CaseTypeFlowEntity>) caseTypeFlowRepository.findAll())
                .stream()
                .map(caseTypeFlowEntity -> {
                    CaseType caseType = new CaseType(caseTypeFlowEntity.getCaseType());
                    return new CaseTypeFlow(
                            caseTypeFlowEntity.getId(),
                            caseTypeFlowEntity.getStage(),
                            caseTypeFlowEntity.getOrder(),
                            caseType
                    );
                }).toList();
    }

    @Override
    public CaseTypeFlow saveCaseTypeFlow(CaseTypeFlowDto request, CaseTypeEntity caseType) {
        CaseTypeFlowEntity caseTypeFlow = CaseTypeFlowEntity.builder()
                .stage(request.stage())
                .order(request.order())
                .caseType(caseType)
                .build();
        caseTypeFlowRepository.save(caseTypeFlow);

        return new CaseTypeFlow(caseTypeFlow);
    }

    @Override
    public void updateCaseTypeFlow(CaseTypeFlowEntity caseTypeFlow, CaseTypeFlowDto input, CaseTypeEntity caseType) {
        caseTypeFlow.setOrder(input.order());
        caseTypeFlow.setStage(input.stage());
        caseTypeFlow.setCaseType(caseType);
        caseTypeFlowRepository.save(caseTypeFlow);
    }

    @Override
    public void deleteCaseTypeFlow(Long id) {
        caseTypeFlowRepository.deleteById(id);
    }
}
