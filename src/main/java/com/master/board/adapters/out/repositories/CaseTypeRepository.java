package com.master.board.adapters.out.repositories;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseTypeRepository extends JpaRepository<CaseTypeEntity,Long> {

}
