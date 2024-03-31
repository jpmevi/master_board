package com.master.board.adapters.out.repositories;

import com.master.board.adapters.out.entities.CaseTypeFlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseTypeFlowRepository extends JpaRepository<CaseTypeFlowEntity,Long> {

}
