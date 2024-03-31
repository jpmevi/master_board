package com.master.board.domain.models;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.CaseTypeFlowEntity;

public record CaseTypeFlow(
        Integer id,
        String stage,
        Integer order,
        CaseType caseType
){
        public CaseTypeFlow(CaseTypeFlowEntity caseTypeFlowEntity) {
                this(
                        caseTypeFlowEntity.getId(),
                        caseTypeFlowEntity.getStage(),
                        caseTypeFlowEntity.getOrder(),
                        new CaseType(caseTypeFlowEntity.getCaseType())
                );
        }
        @Override
        public Integer id() {
                return id;
        }

        @Override
        public String stage() {
                return stage;
        }

        @Override
        public Integer order() {
                return order;
        }

        @Override
        public CaseType caseType() {
                return caseType;
        }
}
