package com.master.board.adapters.out.repositories;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {

    @Query(
            value = "SELECT * FROM project p WHERE p.user_id = :userId",
            nativeQuery = true)
    List<ProjectEntity> findAllByProjectManager(@Param("userId") Long userId);
}
