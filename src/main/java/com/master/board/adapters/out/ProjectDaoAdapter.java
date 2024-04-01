package com.master.board.adapters.out;

import com.master.board.adapters.out.entities.CaseTypeEntity;
import com.master.board.adapters.out.entities.ProjectEntity;
import com.master.board.adapters.out.entities.UserEntity;
import com.master.board.adapters.out.repositories.ProjectRepository;
import com.master.board.application.dao.ProjectDAO;
import com.master.board.application.dto.CaseTypeDto;
import com.master.board.application.dto.ProjectDTO;
import com.master.board.application.dto.RegisterDto;
import com.master.board.domain.models.Project;
import com.master.board.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
    public Optional<ProjectEntity> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> findAllProjects() {
        return ((List<ProjectEntity>) projectRepository.findAll())
                .stream()
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
                })
                .toList();
    }

    public ProjectEntity saveProject(ProjectDTO request, UserEntity user) {
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
        return project;
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


}
