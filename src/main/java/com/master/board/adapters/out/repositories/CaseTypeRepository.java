package com.master.board.adapters.out.repositories;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseTypeRepository extends JpaRepository<CaseTypeEntity,Long> {

    @Query(
            value = "SELECT * FROM case_type c WHERE c.name LIKE %:caseTypeName%",
            nativeQuery = true)
    List<CaseTypeEntity> findByName(@Param("caseTypeName") String caseTypeName);

    @Query(
            value = "SELECT * FROM case_type c WHERE c.project_id = :projectId",
            nativeQuery = true)
    List<CaseTypeEntity> findAllByProject(@Param("projectId") Long projectId);


}
