package com.master.board.application.dao;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.ProjectEntity;
import com.master.board.adapters.out.entities.UserEntity;
import com.master.board.application.dto.CaseTypeDto;
import com.master.board.application.dto.ProjectDTO;
import com.master.board.application.dto.RegisterDto;
import com.master.board.domain.models.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProjectDAO {

    Optional<Project> find(Long id);

    Optional<ProjectEntity> findById(Long id);
    Page<Project> findAllProjects(Pageable pageable);
    List<Project> findAllProjectsByProjectManager (Long userId);
    public Project saveProject(ProjectDTO request, UserEntity user);
    void updateProject(ProjectEntity project, ProjectDTO request, UserEntity user);
    void deleteProject(Long id);

    List<Map<String, Object>> hoursAndMoneySpecificProject(Long projectId);
    List<Map<String, Object>> hoursAndMoneyPerProject();
    List<Map<String, Object>> projectWithMoreCanceledCases();
    List<Map<String, Object>> projectWithMostReportedFinishedCards();

    List<Map<String, Object>> hoursAndMoneyPerCaseType(String caseTypeName);
}
