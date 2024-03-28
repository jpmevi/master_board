package com.master.board.application.dao;

import com.master.board.adapters.out.entities.UserEntity;
import com.master.board.application.dto.RegisterDto;
import com.master.board.domain.models.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectDAO {

    Optional<Project> find(Long id);
    List<Project> findAllProjects();

    Optional<Project> saveProject(RegisterDto project);
    void updateProject(UserEntity user,RegisterDto input);
    void deleteProject(Long id);
}
