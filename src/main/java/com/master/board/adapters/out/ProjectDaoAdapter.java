package com.master.board.adapters.out;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.ProjectEntity;
import com.master.board.adapters.out.entities.UserEntity;
import com.master.board.adapters.out.repositories.ProjectRepository;
import com.master.board.application.dao.ProjectDAO;
import com.master.board.application.dto.CaseTypeDto;
import com.master.board.application.dto.ProjectDTO;
import com.master.board.application.dto.RegisterDto;
import com.master.board.domain.models.CaseType;
import com.master.board.domain.models.Project;
import com.master.board.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class ProjectDaoAdapter implements ProjectDAO {
    private final ProjectRepository projectRepository;

    @Override
    public Optional<Project> find(Long id) {
        return projectRepository.findById(id)
                .map(projectEntity -> {
                    User user = new User(projectEntity.getUser());
                    return new Project(
                            projectEntity.getId(),
                            user,
                            projectEntity.getName(),
                            projectEntity.getDescription(),
                            projectEntity.getBackground_url(),
                            projectEntity.getIsPublic(),
                            projectEntity.getIsActive(),
                            projectEntity.getDisabled_reason(),
                            projectEntity.getCreatedAt(),
                            projectEntity.getUpdatedAt()
                    );
                });
    }


    @Override
    public List<Project> findAllProjectsByProjectManager(Long userId) {
        return  ((List<ProjectEntity>) projectRepository.findAllByProjectManager(userId))
                .stream()
                .map(projectEntity -> {
                    return new Project(
                            projectEntity.getId(),
                            new User(projectEntity.getUser()),
                            projectEntity.getName(),
                            projectEntity.getDescription(),
                            projectEntity.getBackground_url(),
                            projectEntity.getIsPublic(),
                            projectEntity.getIsActive(),
                            projectEntity.getDisabled_reason(),
                            projectEntity.getCreatedAt(),
                            projectEntity.getUpdatedAt()
                    );
                }).toList();
    }
    @Override
    public Optional<ProjectEntity> findById(Long id) {
        return projectRepository.findById(id);
    }


    @Override
    public Page<Project> findAllProjects(Pageable pageable) {
        Page<ProjectEntity> projectEntitiesPage = projectRepository.findAll(pageable);
        List<Project> projects = projectEntitiesPage.getContent().stream()
                .map(projectEntity -> new Project(
                            projectEntity.getId(),
                            new User (projectEntity.getUser()),
                            projectEntity.getName(),
                            projectEntity.getDescription(),
                            projectEntity.getBackground_url(),
                            projectEntity.getIsPublic(),
                            projectEntity.getIsActive(),
                            projectEntity.getDisabled_reason(),
                            projectEntity.getCreatedAt(),
                            projectEntity.getUpdatedAt()
                    ))
                .toList();

        return new PageImpl<>(projects, pageable, projectEntitiesPage.getTotalElements());
    }
    public Project saveProject(ProjectDTO request, UserEntity user) {
        ProjectEntity project = ProjectEntity.builder()
                .user(user)
                .name(request.name())
                .description(request.description())
                .background_url(request.background_url())
                .isPublic(request.is_public())
                .isActive(request.is_active())
                .disabled_reason(request.disabled_reason())
                .build();
        projectRepository.save(project);
        return new Project(project);
    }

    @Override
    public void updateProject(ProjectEntity project, ProjectDTO request, UserEntity user) {
        project.setUser(user);
        project.setName(request.name());
        project.setDescription(request.description());
        project.setBackground_url(request.background_url());
        project.setIsPublic(request.is_public());
        project.setIsActive(request.is_active());
        project.setDisabled_reason(request.disabled_reason());
        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public List<Map<String, Object>> hoursAndMoneySpecificProject(Long projectId){
        List<Object[]> data = projectRepository.hoursAndMoneySpecificProject(projectId);
        List<Map<String, Object>> formattedData = new ArrayList<>();

        for (Object[] row : data) {
            Map<String, Object> projectData = new HashMap<>();
            projectData.put("proyecto", row[0]);
            projectData.put("horas", row[1]);
            projectData.put("dinero", row[2]);
            formattedData.add(projectData);
        }
        return formattedData;
    }
    @Override
    public List<Map<String, Object>> hoursAndMoneyPerProject(){
        List<Object[]> data = projectRepository.hoursAndMoneyPerProject();

        List<Map<String, Object>> formattedData = new ArrayList<>();

        for (Object[] row : data) {
            Map<String, Object> projectData = new HashMap<>();
            projectData.put("proyecto", row[0]);
            projectData.put("horas", row[1]);
            projectData.put("dinero", row[2]);
            formattedData.add(projectData);
        }
        return formattedData;
    }

    @Override
    public List<Map<String, Object>> projectWithMoreCanceledCases(){
        List<Object[]> data = projectRepository.projectWithMoreCanceledCases();

        List<Map<String, Object>> formattedData = new ArrayList<>();

        for (Object[] row : data) {
            Map<String, Object> projectData = new HashMap<>();
            projectData.put("project_id", row[0]);
            projectData.put("numberCanceledCases", row[1]);
            projectData.put("projectName", row[2]);
            formattedData.add(projectData);
        }
        return formattedData;
    }
    @Override
    public List<Map<String, Object>> projectWithMostReportedFinishedCards(){
        List<Object[]> data = projectRepository.projectWithMostReportedCards();

        List<Map<String, Object>> formattedData = new ArrayList<>();

        for (Object[] row : data) {
            Map<String, Object> projectData = new HashMap<>();
            projectData.put("projectName", row[0]);
            projectData.put("numPendingCards", row[1]);
            projectData.put("numCompletedCards", row[2]);
            formattedData.add(projectData);
        }
        return formattedData;
    }

    @Override
    public List<Map<String, Object>> hoursAndMoneyPerCaseType(String caseType){
        List<Object[]> data = projectRepository.hoursAndMoneyPerCaseType(caseType);

        List<Map<String, Object>> formattedData = new ArrayList<>();

        for (Object[] row : data) {
            Map<String, Object> projectData = new HashMap<>();
            projectData.put("case_type_name", row[0]);
            projectData.put("total_hours", row[1]);
            projectData.put("total_money_invested", row[2]);
            formattedData.add(projectData);
        }
        return formattedData;
    }

}
